package com.ebanma.cloud.concurrent;

import org.junit.Test;

/**
 * @author 于秦涛
 * @version $ Id: MyArrayQueueTest, v 0.1 2023/03/08 10:08 98077 Exp $
 */
public class MyArrayQueueTest {

    @Test
    public void p2c() throws InterruptedException {
        MyArrayQueue queue = new MyArrayQueue();

        new Thread(() -> {
            int index = 0;
            while (true) {
                String tmp = Thread.currentThread().getName() + "生产数据:" + index;
                try {
                    queue.put(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tmp);
                index++;
            }
        }).start();

        new Thread(() -> {
            String take = "";
            while (true) {
                try {
                    take = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t\t消费的数据" + take);
            }
        }).start();

        Thread.sleep(1000 * 1);
    }

}

