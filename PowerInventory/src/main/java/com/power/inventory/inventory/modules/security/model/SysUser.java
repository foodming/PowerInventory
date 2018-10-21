package com.power.inventory.inventory.modules.security.model;

import java.util.List;

public class SysUser {

    public SysUser(Long id, String userName, String password)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public SysUser()
    {

    }

    private Long id;

    private String userName;

    private String password;

    private List<SysRole> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}