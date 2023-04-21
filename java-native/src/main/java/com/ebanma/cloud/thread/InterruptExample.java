package com.ebanma.cloud.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new MyThread1();
//        thread1.start();
//        thread1.interrupt();
//        System.out.println("Main run");

//        Thread thread = new Thread(()->{
//            new InterruptExample().run();
//        });
//        thread.run();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //中断所有线程（相当于调用interrupt方法）
        executorService.shutdownNow();
        //中断某一个线程
        Future<?> future = executorService.submit(() -> {
            //
        });
        future.cancel(true);
        System.out.println("Main run");

    }

    public void run() {
        Thread thread2 = new MyThread2();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        thread2.interrupt();
    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            System.out.println(interrupted());
            Long startTime=System.currentTimeMillis();
            while (!interrupted()) {
                //
            }
            System.out.println(System.currentTimeMillis()-startTime);
            System.out.println(interrupted());
            System.out.println("Thread end");
        }
    }
}

