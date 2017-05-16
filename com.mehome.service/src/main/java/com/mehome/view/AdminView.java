package com.mehome.view;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.NetUtils;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html")
public class AdminView {
    @Autowired
    private IAuthorizeService authorizeService;

    //平台管理员登录，企业管理员登录
    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password) {
        HttpSession session = request.getSession();
        String loginIp = NetUtils.getIpAddress(request);
        String loginHeaders = NetUtils.getHeader(request);
        AdministratorBean administratorBean = authorizeService.login(username, password, loginIp, loginHeaders);
        if (null == administratorBean) {
            return "/";
        } else {
            if (RoleEnum.COMPANY.getRole().equals(administratorBean.getRole())) {
                return "redirect:/html/company" + administratorBean.getCompanyId() + ".html";
            } else if (RoleEnum.PLATFORM.getRole().equals(administratorBean.getRole()) || RoleEnum.SUPER.getRole().equals(administratorBean.getRole())) {
                return "redirect:/html/platform/home.html";
            } else {
                return "redirect:/html/404.html";
            }
        }
    }

    @RequestMapping(path = "/")
    public String login() {
        return "signin";
    }
}
