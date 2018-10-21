package com.power.inventory.inventory.modules.security.dao;
import com.power.inventory.inventory.modules.security.model.SysPermission;
import com.power.inventory.inventory.modules.security.model.SysUser;

import java.util.List;

public interface ActiveSysUserDao {

    public SysUser findUserByUserName(String username);
    public List<SysPermission> findPermissionByUserId(int userId);
    public List<SysPermission> findPermissionByUserName(String username);
    public List<SysPermission> findPermissionByRoleId(int roleId);

}
