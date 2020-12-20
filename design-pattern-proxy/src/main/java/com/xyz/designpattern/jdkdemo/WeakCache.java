package com.xyz.designpattern.jdkdemo;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author gaoxugang
 * @data 2020/12/13  14:29
 * @description java 弱缓存
 *
 * @param <K> key类型
 * @param <P> 参数类型
 * @param <V> 值类型
 */
public class WeakCache<K, P, V> {
    /**
     * 引用队列
     */
    private final ReferenceQueue<K> refQueue = new ReferenceQueue<>();

    private final ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map = new ConcurrentHashMap<>();

    private final ConcurrentMap<Supplier<V>, Boolean> reverseMap = new ConcurrentHashMap<>();

    /**
     * subKey工厂
     */
    private final BiFunction<K, P, ?> subKeyFactory;

    /**
     * 值工厂
     */
    private final BiFunction<K, P, V> valueFactory;


    /**
     * 构造参数
     * @param subKeyFactory
     * @param valueFactory
     */
    public WeakCache(BiFunction<K, P, ?> subKeyFactory, BiFunction<K, P, V> valueFactory) {
        this.subKeyFactory = Objects.requireNonNull(subKeyFactory);
        this.valueFactory = Objects.requireNonNull(valueFactory);
    }

    /**
     * 从缓存中获取值
     * @param key
     * @param parameter
     * @return
     */
    public V get(K key,P parameter) {
        // 判断parameter 参数不能为空
        Objects.requireNonNull(parameter);

        expungeStaleEntries();

        // 获取缓存key
        Object cacheKey = CacheKey.valueOf(key, refQueue);

        // 获取值
        ConcurrentMap<Object, Supplier<V>> valuesMap = map.get(cacheKey);
        if (valuesMap == null) {
            ConcurrentMap<Object, Supplier<V>> oldValuesMap = map.putIfAbsent(cacheKey, valuesMap = new ConcurrentHashMap<>());
            if (oldValuesMap != null) {
                valuesMap = oldValuesMap;
            }
        }

        // 获取subKey
        Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
        Supplier<V> supplier = valuesMap.get(subKey);
        Factory factory = null;

        while (true) {

            if (supplier != null) {
                V value = supplier.get();
                if (value != null) {
                    return value;
                }
            }

            // 延迟初始化工厂类
            if (factory == null) {
                factory = new Factory(key, parameter, subKey, valuesMap);
            }

            if (supplier == null) {
                supplier = valuesMap.putIfAbsent(subKey, factory);
                if (supplier == null) {
                    supplier = factory;
                }
            } else {
                if (valuesMap.replace(subKey, supplier, factory)) {
                    supplier = factory;
                } else {
                    supplier = valuesMap.get(subKey);
                }
            }
        }
    }

    public boolean containsValue(V value) {
        Objects.requireNonNull(value);
        expungeStaleEntries();
        return reverseMap.containsValue(null);
    }

    public int size() {
        expungeStaleEntries();
        return reverseMap.size();
    }


    private void expungeStaleEntries() {
        CacheKey<K> cacheKey;
        while ((cacheKey = (CacheKey<K>)refQueue.poll()) != null) {
            cacheKey.expungeFrom(map, reverseMap);
        }
    }

    /**
     * 工厂类
     */
    private final class Factory implements Supplier<V> {

        private final K key;
        private final P parameter;
        private final Object subKey;
        private final ConcurrentMap<Object, Supplier<V>> valuesMap;

        Factory(K key, P parameter, Object subKey,
                ConcurrentMap<Object, Supplier<V>> valuesMap) {
            this.key = key;
            this.parameter = parameter;
            this.subKey = subKey;
            this.valuesMap = valuesMap;
        }

        @Override
        public synchronized V get() {
            Supplier<V> supplier = valuesMap.get(subKey);
            if (supplier != this) {
                return null;
            }

            V value = null;

            try {
                value = Objects.requireNonNull(valueFactory.apply(key, parameter));
            }finally {
                if (value == null) {
                    valuesMap.remove(subKey, this);
                }
            }

            assert value != null;

            return null;
        }
    }

    private interface Value<V> extends Supplier<V> {}

    private static final class LookupValue<V> implements Value<V> {

        private final V value;

        LookupValue(V value) {
            this.value = value;
        }

        @Override
        public V get() {
            return value;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(value);
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj instanceof Value && this.value == ((Value<?>) obj).get();
        }
    }


    private static final class CacheValue<V>
            extends WeakReference<V> implements Value<V>
    {
        private final int hash;

        CacheValue(V value) {
            super(value);
            this.hash = System.identityHashCode(value); // compare by identity
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            V value;
            return obj == this ||
                    obj instanceof Value &&
                            (value = get()) != null &&
                            value == ((Value<?>) obj).get(); // compare by identity
        }
    }


    private static final class CacheKey<K> extends WeakReference<K> {

        private static final Object NULL_KEY = new Object();

        static <K> Object valueOf(K key, ReferenceQueue<K> refQueue) {
            return key == null ? NULL_KEY : new CacheKey<>(key, refQueue);
        }

        private final int hash;

        private CacheKey(K key, ReferenceQueue<K> refQueue) {
            super(key, refQueue);
            this.hash = System.identityHashCode(key);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            K key;
            return obj == this ||
                    obj != null &&
                            obj.getClass() == this.getClass() &&
                            // cleared CacheKey is only equal to itself
                            (key = this.get()) != null &&
                            // compare key by identity
                            key == ((CacheKey<K>) obj).get();
        }

        void expungeFrom(ConcurrentMap<?, ? extends ConcurrentMap<?, ?>> map,
                         ConcurrentMap<?, Boolean> reverseMap) {
            ConcurrentMap<?, ?> valuesMap = map.remove(this);
            if (valuesMap != null) {
                for (Object cacheValue : valuesMap.values()) {
                    reverseMap.remove(cacheValue);
                }
            }
        }
    }









}
