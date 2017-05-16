package com.mehome.view;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.utils.Permits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/html/company")
public class CompanyView {
    @Permits(role = {RoleEnum.COMPANY, RoleEnum.PLATFORM}, needLogin = true)
    @RequestMapping("/users")
    public String index(@RequestParam(value = "companyId", required = true) String companyId) {
        return "company";
    }
}
