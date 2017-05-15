package com.mehome.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;
import com.mehome.utils.Permits;

@Controller
@RequestMapping("/html/house")
public class HouseView {
	@Autowired
	private IHouseService houseService;
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/list")
	public ModelAndView list(@RequestBody HouseBean house){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("house");
		List<HouseBean> houseList=houseService.getListByCondition(house);
		Long houseSize=houseService.getSizeByCondition(house);
		mav.addObject("houseList", houseList);
		mav.addObject("houseTotalCount", houseSize);
		return mav;
	}
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/update")
	public ModelAndView update(@RequestBody HouseBean house){
		ModelAndView mav=new ModelAndView();
		String ret=houseService.updateHouse(house);
		mav.setViewName("house");
		mav.addObject("updateRet", ret);
		return mav;
	}
	
}
