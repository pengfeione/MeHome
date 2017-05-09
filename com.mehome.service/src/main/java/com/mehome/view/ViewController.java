package com.mehome.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by renhui on 2017/5/8.
 */
@Controller
public class ViewController {
    @RequestMapping("/get")
    public String get(){
        return "index";
    }
}
