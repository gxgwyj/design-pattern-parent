package com.xyz.designpattern.factory.simplefactory;


/**
 * 类: Factory <br>
 * 描述: 简单工厂模式（静态工厂方法模式）<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月08日 19:31
 */
public class SimpleFactory {

    private static String PHONE = "Phone";
    private static String TELEVISION = "Television";

    /**
     * 根据类别名称创建对象
     * @param type
     * @return
     */
    public static Goods createGoodsByType(String type){
        if (PHONE.equals(type)){
            return new Phone();
        }
        if (TELEVISION.equals(type)){
            return new Television();
        }
        return null;
    }

    /**
     * 根据类别创建对象(使用反射具有性能损耗)
     * @param clazz
     * @return
     */
    public static Goods createGoodsByClassType(Class<? extends Goods> clazz){
        try {
            return (Goods)clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Goods goods = SimpleFactory.createGoodsByType(TELEVISION);
        System.out.println(goods.getRealPrice());
        Goods phone = SimpleFactory.createGoodsByClassType(Phone.class);
        System.out.println(phone.getRealPrice());
    }
}
