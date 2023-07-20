package com.ebanma.cloud.enumtest;

import java.util.Iterator;

public enum Test {
    APPLE(1,100),BANANA(2,200);
    private int value;
    private int id;

    Test(){}

    @Override
    public String toString() {
        return "Test{" +
                "value=" + value +
                ", id=" + id +
                '}';
    }

    Test(int id , int value) {
        this.id = id ;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        System.out.println(getTest(2));
    }

    public static Test getTest(int id){
//        if(id == APPLE.getId()){
//            return APPLE;
//        }else if(id == BANANA.getId()){
//            return BANANA;
//        }
//        return null;
        //枚举类通过一个属性直接获取整个枚举对象。
        for (Test type : Test.values()) {
            if (id==type.getId()) {
                return type;
            }
        }

        return null;
    }
}
