package com.ebanma.cloud.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 于秦涛
 * @version $ Id: DynamicProxy, v 0.1 2023/03/10 13:35 98077 Exp $
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    /**
     * proxy 被代理的真实对象
     * method 被代理的真实对象的某个方法的Method对象
     * args Method对象所接受的参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before();
        Object result = method.invoke(target, args);
        //AOP的思想,可以拦截方法，例如下面的代码可以获取方法名。
        System.out.println(method.getName());
        //after();
        return result;
    }

    private void after() {
        System.out.println("before");
    }

    private void before() {
        System.out.println("after");
    }

    public static void main(String[] args) {
//        Hello hello = new HelloImpl();
//        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(),
//                dynamicProxy);
//        helloProxy.say("DDD");
//        helloProxy.run();
        Hello hello = new TestClassIsOK();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy);
        helloProxy.say("DDD");
        helloProxy.run();
    }

}

