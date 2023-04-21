package com.ebanma.cloud.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author 于秦涛
 * @version $ Id: QueueTest, v 0.1 2023/03/01 15:58 98077 Exp $
 */
public class QueueTest {

    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值 " + i);
        }
    }

    @Test
    public void linkedBlockQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值 " + i);
        }
    }

    @Test
    public void synchronousQueue() throws InterruptedException {
        SynchronousQueue<Object> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
//                Thread.sleep(1000);
                queue.take();
                System.out.println("删除成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(1000 * 60);
    }

}

