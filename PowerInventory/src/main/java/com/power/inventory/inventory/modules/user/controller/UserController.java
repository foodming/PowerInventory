package com.power.inventory.inventory.modules.user.controller;

import com.alibaba.fastjson.JSON;
import com.power.inventory.inventory.common.Protocal.*;
import com.power.inventory.inventory.modules.user.Protocal.UserDataRequest;
import com.power.inventory.inventory.modules.user.Protocal.UserDataResponse;
import com.power.inventory.inventory.modules.user.model.User;
import com.power.inventory.inventory.modules.user.service.UserService;
import com.power.inventory.inventory.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    private static int ADD_USER_SUCCESS = 0;
    private static int ADD_USER_ERR = -1;
    private static int USER_EXIST_ERR = -2;

    private static int EDIT_USER_SUCCESS = 0;
    private static int EDIT_USER_ERR = -3;

    private static int DELETE_USER_SUCCESS = 0;
    private static int DELETE_USER_ERR = -4;

    private static int UPDATE_USER_STS_SUCCESS = 0;
    private static int UPDATE_USER_STS_ERR = -5;

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/base_data/user/add"})
    @ResponseBody
    public String addUser(Model model,
                          @RequestBody UserDataRequest user,
                          HttpServletRequest request, HttpServletResponse response) {

        int rn = ADD_USER_SUCCESS;

        User likeUser = new User();
        likeUser.setCode(user.getCode());

        List<User> users = userService.findUser(
                1L, 1L,
                likeUser,
                null,
                null);

        //如果用户编号已经存在
        if (users != null && users.size() > 0) {
            rn = USER_EXIST_ERR;

        } else {

            User userRecord = new User();
            userRecord.setCode(user.getCode());
            userRecord.setUserName(user.getUserName());
            userRecord.setPassword(user.getPassword());
            userRecord.setTelNo(user.getTelNo());
            userRecord.setRemark(user.getRemark());
            userRecord.setStatus(user.getStatus());

            try {
                rn = userService.addUser(userRecord);
            } catch (Exception e) {
                rn = ADD_USER_ERR;
            }
        }
        UserDataResponse rsp = new UserDataResponse();
        if (rn >= 0) {
            rsp.setResult(ADD_USER_SUCCESS);
        } else {
            rsp.setResult(rn);
        }

        String jsonStr = JSON.toJSONString(rsp);
        return jsonStr;
    }

    @PostMapping(value = {"/base_data/user/edit"})
    @ResponseBody
    public String editUser(Model model,
                           @RequestBody UserDataRequest user,
                           HttpServletRequest request, HttpServletResponse response) {

        int rn = EDIT_USER_SUCCESS;

            User userRecord = new User();
            userRecord.setCode(user.getCode());
            userRecord.setUserName(user.getUserName());
            userRecord.setPassword(user.getPassword());
            userRecord.setTelNo(user.getTelNo());
            userRecord.setRemark(user.getRemark());
            userRecord.setStatus(user.getStatus());

            try {
                rn = userService.editUser(userRecord);
            } catch (Exception e) {
                rn = EDIT_USER_ERR;
            }

        UserDataResponse rsp = new UserDataResponse();
        if (rn >= 0) {
            rsp.setResult(EDIT_USER_SUCCESS);
        } else {
            rsp.setResult(rn);
        }

        String jsonStr = JSON.toJSONString(rsp);
        return jsonStr;
    }

    private List<Integer> pageSizeList = new ArrayList<Integer>(Arrays.asList(10, 25, 50, 100));

    @RequestMapping(value = "/base_data/user/get")
    public String getUser(Model model,
                          //查询条件
                          @ModelAttribute User likeUser,
                          //请求的Page信息
                          @ModelAttribute PageRequest pageRequest,
                          HttpServletRequest request) {

        Long current = 1L;
        Long pageSize = 20L;

        if (pageRequest.getCurrentPage() > 0) {
            current = pageRequest.getCurrentPage();
        }

        if (pageRequest.getPageSize() > 0) {
            pageSize = pageRequest.getPageSize();
        }

        PageResponse pageResponse = new PageResponse();
        pageResponse.setUrl("/base_data/user/get");
        pageResponse.setCurrentPage(current);
        pageResponse.setPageSize(pageSize);

        List<User> users = userService.findUser(
                current, pageSize,
                likeUser,
                null,
                null);

        //设置总的记录数
        Long totalCounts = userService.getTotalCounts(likeUser);
        pageResponse.setTotalRecords(totalCounts);

        //设置总页数
        Long totalPages = 0L;
        if (totalCounts > 0) {
            totalPages = (totalCounts - 1) / pageSize + 1;
        }
        pageResponse.setTotalPages(totalPages);


        //设置分页显示的起始和结束页号
        Long windowPages = 8L;
        Long fromPage = current - windowPages / 2;
        if (fromPage < 0) {
            fromPage = 1L;
        }
        Long toPage = fromPage + windowPages - 1;
        if (toPage > totalPages) {
            toPage = totalPages > 0 ? totalPages : 1;
            fromPage = toPage - windowPages;
            if (fromPage < 0) {
                fromPage = 1L;
            }
        }
        pageResponse.setFromPage(fromPage);
        pageResponse.setToPage(toPage);

        //向页面传递接口参数
        model.addAttribute("pageSizeList", pageSizeList);
        model.addAttribute("likeUser", likeUser);
        model.addAttribute("page", pageResponse);
        model.addAttribute("users", users);

        return "userManager";
    }

    @PostMapping(value = {"/base_data/user/del"})
    @ResponseBody
    public String deleteUser(Model model,
                          //删除条件
                          @RequestBody List<String> codeList,
                          HttpServletRequest request) {

       // List<String> codeList = new ArrayList<>();
        int rn = DELETE_USER_ERR;

        if(codeList != null && codeList.size() > 0){
            try {
                rn = userService.deleteUser(codeList);
            } catch (Exception e) {
                rn = DELETE_USER_ERR;
            }
        }

        UserDataResponse rsp = new UserDataResponse();
        if (rn >= 0) {
            rsp.setResult(DELETE_USER_SUCCESS);
        } else {
            rsp.setResult(rn);
        }

        String jsonStr = JSON.toJSONString(rsp);
        return jsonStr;
    }


    @PostMapping(value = {"/base_data/user/setStatus"})
    @ResponseBody
    public String SetUserStatus(Model model,
                             @RequestBody User user,
                             HttpServletRequest request) {

        int rn = UPDATE_USER_STS_ERR;

        if(user != null){
            String code = user.getCode();
            int sts = user.getStatus();

            try {
                rn = userService.updateUserStatus(code, sts);
            } catch (Exception e) {
                rn = UPDATE_USER_STS_ERR;
            }
        }

        UserDataResponse rsp = new UserDataResponse();
        if (rn >= 0) {
            rsp.setResult(UPDATE_USER_STS_SUCCESS);
        } else {
            rsp.setResult(rn);
        }

        String jsonStr = JSON.toJSONString(rsp);
        return jsonStr;
    }

}
