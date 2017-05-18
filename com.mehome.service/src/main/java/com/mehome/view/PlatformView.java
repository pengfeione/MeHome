package com.mehome.view;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.utils.Permits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html/platform")
public class PlatformView {
    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @RequestMapping("/home.html")
    public String index(ModelAndView modelAndView) {
        return "platform";
    }

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @RequestMapping("/companylist.html")
    public String list(ModelAndView modelAndView) {
        return "platformCompanyList";
    }

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @RequestMapping("/companyinfo.html")
    public String companyEdit(@RequestParam(value = "code", required = false) String companyId) {
        return "platformCompanyEdit";
    }
}
