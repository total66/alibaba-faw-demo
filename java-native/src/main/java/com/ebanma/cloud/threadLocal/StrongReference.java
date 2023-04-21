package com.ebanma.cloud.threadLocal;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 于秦涛
 * @version $ Id: ThreadLocal, v 0.1 2023/03/18 12:34 98077 Exp $
 */
public class StrongReference {

    public static void main(String[] args) {
//        Object obj1 = new Object(); //这样的定义默认就是强引用
//        Object obj2 = obj1; //obj2引用赋值
//        obj1 = null; //置空
//        System.gc(); //触发gc
//        System.out.println(obj1); //obj1
//        System.out.println(obj2); //obj2依然还存在
        Set set = new HashSet();
        set.add("hello");
        System.out.println(set.hashCode());
        Object next = set.iterator().next();
        System.out.println(next.toString());
    }



}

