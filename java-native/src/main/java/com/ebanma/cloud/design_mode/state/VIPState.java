package com.ebanma.cloud.design_mode.state;

public class VIPState extends UserState{
    @Override
    public void favorite() {
        System.out.println("I'm vip");
    }

    @Override
    public void comment(String comment) {
        System.out.println("VIP驾到，统统闪开");
    }
}
