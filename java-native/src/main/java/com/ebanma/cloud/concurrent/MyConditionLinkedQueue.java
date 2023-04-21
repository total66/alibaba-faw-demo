package com.ebanma.cloud.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 于秦涛
 * @version $ Id: MyConditionLinkedQueue, v 0.1 2023/03/08 10:24 98077 Exp $
 * 使用Condition协助、通过链表实现阻塞队列
 */
public class MyConditionLinkedQueue {

    //生产者消费者实现模式：2种，通过自带的，或者是condition的方法。

    private LinkedList<Object> data = new LinkedList<>();
    private int maxSize = 10;

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void put(Object Object) throws InterruptedException {
        lock.lock();
        try {
            while(data.size() == maxSize){
                notFull.await();
            }
            data.add(Object);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException{
        lock.lock();
        try {
            while(data.size() == 0){
                notEmpty.await();
            }
            notFull.signalAll();
            Object o = data.remove();
            return o;
        } finally {
            lock.unlock();
        }
    }


}

