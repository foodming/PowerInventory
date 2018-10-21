package com.power.inventory.inventory.modules.user.model;

import java.sql.Timestamp;

public class User {
    private Long id;//ID，自增变量
    private String code;//用户编码
    private String userName;//用户名
    private String password;//密码
    private String telNo;//电话
    private String remark;//备注
    private Integer status;//状态，On/Off
    private Timestamp createTime; //创建时间(和service的接口)
    private Timestamp modifyTime; //变更时间(和service的接口)
    private String strCreateTime; //创建时间(显示/查询用)
    private String strModifyTime; //创建时间(显示/查询用)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStrCreateTime() {
        return strCreateTime;
    }

    public void setStrCreateTime(String strCreateTime) {
        this.strCreateTime = strCreateTime;
    }

    public String getStrModifyTime() {
        return strModifyTime;
    }

    public void setStrModifyTime(String strModifyTime) {
        this.strModifyTime = strModifyTime;
    }
}