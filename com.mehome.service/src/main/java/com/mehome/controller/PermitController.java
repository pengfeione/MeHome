package com.mehome.controller;

import com.mehome.service.iface.IAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by renhui on 2017/5/8.
 * 用户权限表
 */
@RestController
@RequestMapping("/permit")
public class PermitController {
    @Autowired
    private IAuthorizeService authorizeService;

    //平台管理员登录，企业管理员登录
    //根据管理员id获取可以访问的功能模块
    //权限管理
    @RequestMapping(path = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        authorizeService.login(request.getParameter("username"), request.getParameter("password"));
        return "success";
    }
}
