package com.power.inventory.inventory.modules.login.controller;

import com.alibaba.fastjson.JSON;
import com.power.inventory.inventory.common.constant.EnviormentConst;
import com.power.inventory.inventory.modules.login.protocal.LoginRequest;
import com.power.inventory.inventory.modules.login.protocal.LoginResponse;
import com.power.inventory.inventory.modules.security.model.ActiveSysUser;
import com.power.inventory.inventory.modules.security.model.SysUser;
import com.power.inventory.inventory.modules.security.service.SysUserService;
import com.power.inventory.inventory.modules.security.service.ActiveSysUserManager;
import com.power.inventory.inventory.utils.IpAddressUtil;
import com.power.inventory.inventory.utils.RedisDaoImpl;
import com.power.inventory.inventory.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

//test for git

/**
 * 进入登陆页面
 */
@Controller
public class LoginController {

    @Autowired
    SysUserService uService;

    @Autowired
    ActiveSysUserManager activeSysUserManager;

    @Autowired
    RedisDaoImpl redisDao;


    @RequestMapping(value = {"/login"})
    public String Login(Model model) {

            return "login";
    }

    @PostMapping(value = {"/login"})
    @ResponseBody
    public String LoginSubmit(Model model,
                              @RequestBody LoginRequest user,
                              HttpServletRequest request, HttpServletResponse response) {

        String userName = user.getUsername();
        String password = user.getPassword();
        LoginResponse loginResponse = new LoginResponse();
        //数据库验证登录
        SysUser sysUser = uService.Login(userName, password);

        if (sysUser != null) {
            String token = TokenUtils.sign(userName, 1000 * 60 * 3);
            //response.setHeader("x-auth-token", token);//for test

            ActiveSysUser activeSysUser = new ActiveSysUser();

            //登录用户
            activeSysUser.setUser(sysUser);

            //登录IP地址
            String remoteAddr = IpAddressUtil.getIpAdrress(request);
            if(remoteAddr != null){
                activeSysUser.setIpAddress(remoteAddr);
            }

            //登录token
            activeSysUser.setToken(token);

            //登录时间
            Date nowTime = new Date();
            activeSysUser.setLoginTime(nowTime);

            //把登录用户的信息及权限保存到内存
            activeSysUserManager.activeSysUserList.put(userName, activeSysUser);


            //把登录信息及权限保存到Session中
            HttpSession session = request.getSession();
            session.setAttribute(EnviormentConst.SESSION_USER_KEY, userName);
            redisDao.set(userName, activeSysUser);


            loginResponse.setResult(0);
            loginResponse.setUserName(userName);
            loginResponse.setToken(token);
        //登录失败
        } else {
            loginResponse.setResult(1);
            loginResponse.setUserName(userName);
            loginResponse.setToken("");
        }

        String jsonStr = JSON.toJSONString(loginResponse);
        return jsonStr;
    }
    @RequestMapping(value = {"/set"})
    @ResponseBody
    public String SetData(Model model, HttpServletRequest request, HttpServletResponse response) {

        Map<String,String> map = new HashMap<>();


        String sessionID = request.getSession().getId();
        map.put("name1", "test1");
        map.put("id", sessionID);

        String jsonStr = JSON.toJSONString(map);
        request.getSession().setAttribute("name1", "test1");
        return jsonStr;
    }


    @RequestMapping(value = {"/get"})
    @ResponseBody
    public String GetData(Model model, HttpServletRequest request, HttpServletResponse response) {

        String sessionID = request.getSession().getId();
        String name1 = (String)request.getSession().getAttribute("name1");

        return name1;
    }

}
