package com.power.inventory.inventory.modules.security.service;

import com.power.inventory.inventory.modules.security.model.ActiveSysUser;
import com.power.inventory.inventory.modules.security.model.SysRole;
import com.power.inventory.inventory.modules.security.model.SysUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ActiveSysUserManager {

    //登录用户管理表
    public Map<String, ActiveSysUser> activeSysUserList = new HashMap<>();

    //角色表，启动时加载全部的角色和权限
    //public List<SysRole> sysRoleList = new ArrayList<>();
}

