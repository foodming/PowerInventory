package com.power.inventory.inventory.modules.security.service;

import com.power.inventory.inventory.common.constant.EnviormentConst;
import com.power.inventory.inventory.modules.security.dao.ActiveSysUserDao;
import com.power.inventory.inventory.modules.security.model.ActiveSysUser;
import com.power.inventory.inventory.modules.security.model.SecurityForm;
import com.power.inventory.inventory.utils.RedisDaoImpl;
import com.power.inventory.inventory.utils.SpringUtil;
import com.power.inventory.inventory.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    public LoginInterceptor() {
        activeSysUserDao = SpringUtil.getBean(ActiveSysUserDao.class);
        activeSysUserManager = SpringUtil.getBean(ActiveSysUserManager.class);
        redisDao = SpringUtil.getBean(RedisDaoImpl.class);
    }

    ActiveSysUserDao activeSysUserDao;

    ActiveSysUserManager activeSysUserManager;

    RedisDaoImpl redisDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String userName = (String)request.getSession().getAttribute(EnviormentConst.SESSION_USER_KEY);
        ActiveSysUser activeSysUser = null;
        if(userName != null) {
            activeSysUser = (ActiveSysUser) redisDao.get(userName);
        }else{
            //未登录
                try{
                    response.sendRedirect("/login");
                }catch(Exception e){}
        }


        return activeSysUser!=null ? true : false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
