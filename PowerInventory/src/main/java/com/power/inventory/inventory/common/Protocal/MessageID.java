package com.power.inventory.inventory.common.Protocal;

public enum MessageID{

    LOGIN_REQ(0x0001),
    LOGIN_RSP(0x8001),
    Add_USER_REQ(0x0002),
    Add_USER_RSP(0x8002);

    private final int value;

    //构造方法必须是private或者默认
    MessageID(int value) {
        this.value = value;
    }

}