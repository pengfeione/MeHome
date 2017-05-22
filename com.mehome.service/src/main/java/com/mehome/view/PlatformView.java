package com.mehome.view;

import com.mehome.domain.CompanyList;
import com.mehome.enumDTO.InfoOperationEnum;
import com.mehome.enumDTO.OperationTypeEnum;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.service.iface.ICompanyService;
import com.mehome.utils.Permits;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private ICompanyService companyService;


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
    public String companyEdit(@RequestParam(value = "code", required = false) Integer companyId,
                              @RequestParam(value = "op", required = false) String operation,
                              Model model
    ) {
        if (null == companyId) {
            return "404";
        }
        CompanyList companyList = companyService.selectById(companyId);
        if (null == companyList) {
            return "404";
        }
        if (!InfoOperationEnum.contain(operation)) {
            return "404";
        }
        if (InfoOperationEnum.UPDATE.getOperation().equals(operation)) {
            model.addAttribute("update", true);
            model.addAttribute("action", "/api/company/update");
        } else {
            model.addAttribute("update", false);
            model.addAttribute("action", "/api/company/add");
        }
        model.addAttribute("companyInfo", companyList);
        return "platformCompanyInfo";
    }
}
