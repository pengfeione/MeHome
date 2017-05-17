package com.mehome.view;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.NetUtils;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html")
public class AdminView {
    @Autowired
    private IAuthorizeService authorizeService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //平台管理员登录，企业管理员登录
    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, Model model) {
        HttpSession session = request.getSession();
        String loginIp = NetUtils.getIpAddress(request);
        String loginHeaders = NetUtils.getHeader(request);
        try {
            AdministratorBean administratorBean = authorizeService.login(username, password, loginIp, loginHeaders);
            if (null == administratorBean) {
                model.addAttribute("msg", "用户名或密码不正确");
                return "redirect:/html/login.html";
            } else {

                if (RoleEnum.COMPANY.getRole().equals(administratorBean.getRole())) {
                    session.setAttribute("user", administratorBean);
                    redisTemplate.opsForValue().set(session.getId(), administratorBean);
                    session.setMaxInactiveInterval(3000);
                    return "redirect:/html/company/home.html";
                } else if (RoleEnum.PLATFORM.getRole().equals(administratorBean.getRole()) || RoleEnum.SUPER.getRole().equals(administratorBean.getRole())) {
                    session.setAttribute("user", administratorBean);
                    session.setMaxInactiveInterval(3000);
                    return "redirect:/html/platform/home.html";
                } else {
                    return "redirect:/html/404.html";
                }
            }
        } catch (InfoException e) {
            model.addAttribute("msg", e.getMessage());
            return "redirect:/html/login.html";
        }
    }

    @RequestMapping(path = "/login.html")
    public String login() {
        return "login";
    }
}
