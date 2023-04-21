package com.ebanma.cloud.proxy;

public class Test {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy(new HelloImpl());
        helloProxy.say("XXX");
    }
}