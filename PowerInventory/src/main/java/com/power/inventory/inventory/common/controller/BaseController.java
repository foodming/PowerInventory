package com.power.inventory.inventory.common.controller;

import com.power.inventory.inventory.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;




@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/"})
    public String Login(Model model) {
        return "redirect:/login";
    }

    @RequestMapping(value={"/main"})
    public String MainPage(Model model, HttpServletRequest request, HttpServletResponse response){
        String token = request.getParameter("token");
        return "main";
    }


    @RequestMapping(value={"/base_data/emp"})
    public String GotoEmpPage(Model model){
        return "empManager";
    }
}
