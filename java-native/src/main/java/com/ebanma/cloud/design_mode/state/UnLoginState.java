package com.ebanma.cloud.design_mode.state;

import java.util.Random;

public class UnLoginState extends UserState {

    @Override
    public void favorite() {
        if(Math.random()<0.5){
            this.switch2Login();
        }else{
            this.switch2VIP();
        }

        this.context.getState().favorite();
    }

    @Override
    public void comment(String comment) {
        this.switch2Login();
        this.context.getState().comment(comment);
    }

    private void switch2Login() {
        System.out.println("跳转到登录页面!");
        this.context.setState(this.context.STATE_LOGIN);
    }

    private void switch2VIP() {
        System.out.println("尊贵的vip正在登陆!");
        this.context.setState(this.context.STATE_VIP);
    }
}
