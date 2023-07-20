package com.ebanma.cloud.design_mode.state.askforleave.entity;

public enum Status {

    //roleCode：0：员工；1：1级领导；2：2级领导；1000：无权限控制
    WRITING(0,0),
    WAITING_FOR_APPROVAL_1(1,1),
    WAITING_FOR_APPROVAL_2(2,2),
    PASS(3,1000);

    private int stateCode;

    private int roleCode;

    Status(int stateCode, int roleCode) {
        this.stateCode = stateCode;
        this.roleCode = roleCode;
    }

    Status() {
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }
}
