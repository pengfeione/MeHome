package com.mehome.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.Permits;

@Controller
@RequestMapping("/html/order")
public class OrderView {
	@Autowired
	private IOrderService orderService;
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/list")
	public ModelAndView list(@RequestBody OrderBean order){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("order");
		List<OrderBean> orderList=orderService.getListByCondition(order);
		Long orderSize=orderService.getSizeByCondition(order);
		mav.addObject("orderList", orderList);
		mav.addObject("orderTotalCount", orderSize);
		return mav;
	}
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/update")
	public ModelAndView update(@RequestBody OrderBean order){
		ModelAndView mav=new ModelAndView();
		String ret=orderService.updateOrder(order);
		mav.setViewName("order");
		mav.addObject("updateRet", ret);
		return mav;
	}
	
}
