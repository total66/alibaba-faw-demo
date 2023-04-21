package com.ebanma.cloud.proxy;

/**
 * @author 于秦涛
 * @version $ Id: TestClassIsOK, v 0.1 2023/03/16 10:02 98077 Exp $
 */
public class TestClassIsOK implements Hello{

    @Override
    public void say(String name) {
        System.out.println("this is fake");
    }

    public void run(){
        System.out.println("run");
    }

}

