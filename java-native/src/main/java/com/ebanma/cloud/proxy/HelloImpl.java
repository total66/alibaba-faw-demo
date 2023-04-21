package com.ebanma.cloud.proxy;

public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("hello world" + name);
    }

    @Override
    public void run() {
        System.out.println("I'm running");
    }
}