package com.ebanma.cloud.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * @author 于秦涛
 * @version $ Id: MyArrayQueue, v 0.1 2023/03/08 9:55 98077 Exp $
 * 手写数组阻塞队列原理。
 */
public class MyArrayQueue {

    private String[] data = new String[10];

    private int putIndex = 0;
    private int getIndex = 0;
    private int maxSize;

    //多线程抛出异常可以让线程停下来。在多线程中异常的处理方法最好使用抛出的方式。
    public synchronized void put(String element) throws InterruptedException {
        if(maxSize == data.length){
            wait();
        }
        data[putIndex] = element;
        notifyAll();
        ++ maxSize;
        ++ putIndex;
        if(putIndex == data.length) {
            putIndex = 0;
        }
    }

    public synchronized String take() throws InterruptedException {
        if(maxSize == 0){
            wait();
        }
        String result = data[getIndex];
        notifyAll();
        -- maxSize;
        ++ getIndex;
        if(getIndex == data.length){
            getIndex = 0;
        }
        return result;
    }

}

