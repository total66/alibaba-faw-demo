package com.ebanma.cloud.threadLocal;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //在分配一个数组，设置JVM堆内存最大20M，做实验  -Xmx20M。那么新创建的byte[]原有空间一定装不下
        //这时候系统就会垃圾回收，先回收一次，如果不够会把软引用也回收掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}