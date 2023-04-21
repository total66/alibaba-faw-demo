package com.ebanma.cloud.proxy;

public class HelloProxy implements Hello{
    private HelloImpl helloImpl;

    public HelloProxy(HelloImpl helloImpl) {
        this.helloImpl = helloImpl;
    }
    @Override
    public void say(String name) {
        this.before();
        helloImpl.say(name);
        this.after();
    }

    @Override
    public void run() {

    }

    public void before() {
        System.out.println("before");
    }
    public void after() {
        System.out.println("after");
    }
}