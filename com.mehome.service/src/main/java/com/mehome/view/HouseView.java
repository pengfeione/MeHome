package com.mehome.view;

import com.mehome.domain.HouseResource;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/html/house")
public class HouseView {
    @Autowired
    private IHouseService houseService;

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/list")
    public ModelAndView list(@RequestBody HouseBean house) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("house");
        List<HouseBean> houseList = houseService.getListByCondition(house);
        Long houseSize = houseService.getSizeByCondition(house);
        mav.addObject("houseList", houseList);
        mav.addObject("houseTotalCount", houseSize);
        return mav;
    }

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/update")
    public ModelAndView update(@RequestBody HouseBean house) {
        ModelAndView mav = new ModelAndView();
        String ret = houseService.updateHouse(house);
        mav.setViewName("house");
        mav.addObject("updateRet", ret);
        return mav;
    }

    @Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/add")
    public ModelAndView add(@RequestBody HouseBean house) {
        ModelAndView mav = new ModelAndView();
        String ret = houseService.addHouse(house);
        mav.setViewName("house");
        mav.addObject("addRet", ret);
        return mav;
    }

//    @Permits(role = {RoleEnum.PLATFORM, RoleEnum.SUPPLIER})
    @GetMapping(path = "/info")
    public String info(@RequestParam(value = "code", required = false) Integer code, Model model) {
        if (null == code) {
            return "redirect:/html/404.html";
        }
        HouseResource house = houseService.selectById(code);
        if (null == house) {
            return "redirect:/html/404.html";
        }
        return "HouserInfo";
    }
}
