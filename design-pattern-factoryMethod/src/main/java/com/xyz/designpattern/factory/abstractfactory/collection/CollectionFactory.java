package com.xyz.designpattern.factory.abstractfactory.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 类: CollectionFactory <br>
 * 描述: collection 中的经典抽象工厂模式<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月30日 17:41
 */
public class CollectionFactory {
    public static void main(String[] args) {
        // List的迭代器
        List<String> list = new ArrayList<>();
        list.add("name");
        Iterator<String> listIterator = list.iterator();

        Set<String> set = new HashSet<>();
        set.add("name");
        Iterator<String> setIterator = set.iterator();

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("abc");
        linkedList.iterator();
    }
}
