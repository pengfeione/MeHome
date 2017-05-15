package com.mehome.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html/company")
public class CompanyView {
    @RequestMapping("/{companyId}.html")
    public String index(@PathVariable("companyId") String compainId) {
        return "company";
    }
}
