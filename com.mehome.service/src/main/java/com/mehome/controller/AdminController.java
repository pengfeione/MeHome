package com.mehome.controller;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.NetUtils;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by renhui on 2017/5/8.
 * 用户权限表
 */

@Controller
public class AdminController {
    @Autowired
    private IAuthorizeService authorizeService;

    //平台管理员登录，企业管理员登录
    @RequestMapping(path = "/manager/login")
    public String login(HttpServletRequest request, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password) {
        HttpSession session = request.getSession();
        String loginIp = NetUtils.getIpAddress(request);
        String loginHeaders = NetUtils.getHeader(request);
        AdministratorBean administratorBean = authorizeService.login(username, password, loginIp, loginHeaders);
        if (RoleEnum.COMPANY.getRole().equals(administratorBean.getRole())) {
            return "redirect:/company/home/" + administratorBean.getCompanyId() + ".html";
        } else if (RoleEnum.PLATFORM.getRole().equals(administratorBean.getRole()) || RoleEnum.SUPER.getRole().equals(administratorBean.getRole())) {
            return "redirect:/platform/home.html";
        } else {
            return "redirect:/error";
        }
    }

    @RequestMapping(path = "/company/home/{company}")
    public String home(@PathVariable("company") String company) {
        return "companyhome";
    }

    @RequestMapping(path = "/platform/home")
    public String login() {

        return "platform";
    }
}
