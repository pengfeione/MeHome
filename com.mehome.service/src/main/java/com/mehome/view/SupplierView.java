package com.mehome.view;

import java.util.List;

import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.service.iface.ISupplierService;
import com.mehome.utils.Permits;

@Controller
@RequestMapping("/html/supplier")
public class SupplierView {
    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IOrderService orderService;


    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/list")
    public ModelAndView list(@RequestBody SupplierBean supplier) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("supplier");
        List<SupplierBean> supplierList = supplierService.getListByCondition(supplier);
        Long supplierSize = supplierService.getSizeByCondition(supplier);
        mav.addObject("supplierList", supplierList);
        mav.addObject("supplierTotalCount", supplierSize);
        return mav;
    }

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/update")
    public ModelAndView update(@RequestBody SupplierBean supplier) {
        ModelAndView mav = new ModelAndView();
        String ret = supplierService.updateSupplier(supplier);
        mav.setViewName("supplier");
        mav.addObject("updateRet", ret);
        return mav;
    }
}
