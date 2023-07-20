package com.ebanma.cloud.design_mode.state.old;
/**
 * 所有的状态的集合，赋予初始状态，在其他状态下的操作会改变当前状态
 * 每个单独的状态里都有该类作为属性
 */
public class AppContext {
    //1、一个状态机里有多个状态存在
    public static final UserState STATE_LOGIN = new LoginInState();
    public static final UserState STATE_UNLOGIN = new UnLoginState();
    public static final UserState STATE_VIP = new VIPState();
    //2、当前状态设置
    private UserState currentState = STATE_UNLOGIN;
    //3、所有状态配置上状态机。
    {
        STATE_LOGIN.setContext(this);
        STATE_UNLOGIN.setContext(this);
        STATE_VIP.setContext(this);
    }

    public void setState(UserState state) {
        this.currentState = state;
        this.currentState.setContext(this);
    }

    public UserState getState() {
        return this.currentState;
    }

    public void favorite() {
        this.currentState.favorite();
    }

    public void comment(String comment) {
        this.currentState.comment(comment);
    }
}