package com.ebanma.cloud.thread;

public class RunnableThread implements Runnable {
    //通过实现Runnable接口实现线程
    @Override
    public void run() {
        System.out.println("用实现Runnable接口实现线程");
    }

    public static void main(String[] args) {
        RunnableThread instance = new RunnableThread();
        Thread thread = new Thread(instance);
        thread.run();
    }
}
