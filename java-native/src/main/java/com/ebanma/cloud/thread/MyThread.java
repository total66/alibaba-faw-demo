package com.ebanma.cloud.thread;

/**
 * @author 于秦涛
 * @version $ Id: MyThread, v 0.1 2023/02/28 11:29 98077 Exp $
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("this is Thread method");
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        Thread thread=new Thread(myThread);
        thread.run();
    }

}

