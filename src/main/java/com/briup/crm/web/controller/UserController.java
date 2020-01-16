package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.crm.bean.SysUser;

import com.briup.crm.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @ApiOperation(value = "登录功能", notes = "登录功能", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ApiParam(value = "用户名",example = "jingli2") String username, @ApiParam(value = "密码",example = "123456")String password, HttpSession session) {
        String url = "";
        try {

            SysUser user = userService.login(username, password);
            session.setAttribute("user", user);
            url = "index";
        } catch (Exception e) {
            session.setAttribute("msg", e.getMessage());
            url = "login";

        }
        return url;
    }

    //退出
    @ApiOperation(value = "登出功能", notes = "登出功能", httpMethod = "GET")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }
}
