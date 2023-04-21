package com.ebanma.cloud.threadpool;

import com.google.errorprone.annotations.Var;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 于秦涛
 * @version $ Id: PolicyTest, v 0.1 2023/03/01 16:08 98077 Exp $
 */
public class PolicyTest {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(5));


    class Task implements Runnable {
        // 任务名称
        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("线程[ " + Thread.currentThread().getName()
                    + " ]正在执行[ " + this.taskName + " ]任务...");

            try {
                Thread.sleep(1000L * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程[ " + Thread.currentThread().getName()
                    + " ]已执行完[ " + this.taskName + " ]任务！！！");
        }
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(1000L * 100);
    }

    @Test
    public void abort(){
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Task("线程任务" + i));
        }

        executor.shutdown();

    }

}

