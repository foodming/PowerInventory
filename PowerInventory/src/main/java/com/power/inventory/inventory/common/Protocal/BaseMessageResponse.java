package com.power.inventory.inventory.common.Protocal;

public class BaseMessageResponse extends BaseMessage {
    protected int result = 0;
    protected String resultMsg="";

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
