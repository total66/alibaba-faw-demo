package com.ebanma.cloud.threadLocal;

import java.util.concurrent.TimeUnit;

public class threadLocalDemo {
    static ThreadLocal<Person> tl = new ThreadLocal<Person>();
    
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        },"A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        },"B").start();
    }

    static class Person {
        String name = "yangguo";
    }

}