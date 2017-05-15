package com.mehome.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html/page")
public class SystemView {
    @RequestMapping("/{errorCode}.html")
    public String index(@PathVariable("errorCode") String errorCode) {
        return errorCode;
    }
}
