package com.power.inventory.inventory.modules.security.service;

import com.power.inventory.inventory.modules.security.dao.ActiveSysUserDao;

import com.power.inventory.inventory.modules.security.model.SysUser;
import com.power.inventory.inventory.utils.TokenUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Service
public class SysUserService {

    @Autowired
    ActiveSysUserManager activeUserManager;

    @Autowired
    ActiveSysUserDao activeSysUserDao;

    public SysUser Login(String userName, String password) {

        SysUser user = activeSysUserDao.findUserByUserName(userName);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                user = null;//密码不正确
            }
        }
        return user;
    }

    public boolean IsLogin(String userName){
        return activeUserManager.activeSysUserList.containsKey(userName)?true:false;
    }


    public void Logout(String userName){

    }

    public boolean VerifyUserByToken(String userName, String token) {
        return true;
//        ActiveSysUser user = null;
//
//        //验证用户是否存在
//        //1.在内存中验证用户是否存在
//        if (activeUserManager.activeSysUserList.containsKey(userName)) {
//            user = activeUserManager.activeSysUserList.get(userName);
//            //判断token
//            if (user.equals(token)) {
//
//
//            }
//
//        }
//        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//        userDao = factory.getBean(SysUserMapper.class);
//        SysUser userDB = userDao.findByUserName(userName);
//        if (userDB != null) {
//            return true;
//        }
    }
}
