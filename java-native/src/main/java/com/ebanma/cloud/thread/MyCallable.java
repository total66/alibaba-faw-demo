package com.ebanma.cloud.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return "This is a Callable interface";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1、获得继承Callable接口的类
        MyCallable myCallable = new MyCallable();
        // 2、通过步骤1获取的类构造FutureTask类
        FutureTask<String> ft = new FutureTask<>(myCallable);
        // 3、通过步骤2创建的类构造一个新的线程
        Thread thread=new Thread(ft);
        // 4、线程使用start/run方法运行
        thread.start();
        // 5、运行后可以通过ft的get方法获取返回值
        System.out.println(ft.get());
    }
}