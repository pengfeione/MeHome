package com.mehome.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html/platform")
public class PlatformView {
    @RequestMapping("/home.html")
    public String index(ModelAndView modelAndView) {
        return "platform";
    }
}
