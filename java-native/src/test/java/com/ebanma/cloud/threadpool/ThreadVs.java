package com.ebanma.cloud.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 于秦涛
 * @version $ Id: ThreadVs, v 0.1 2023/03/01 15:39 98077 Exp $
 */
public class ThreadVs {

    @Test
    public void oldHandle() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "文档处理开始");
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "文档处理结束");
            }).start();
        }
        Thread.sleep(1000 * 500);
    }

    @Test
    public void newHandle() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "文档处理开始");
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "文档处理结束");
            });
        }

        Thread.sleep(1000 * 500);
    }

}

