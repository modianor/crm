package com.briup.crm.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @ApiOperation(value = "跳转到首页", notes = "跳转到首页", httpMethod = "GET")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }
}
