package com.mehome.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IProductService;
import com.mehome.utils.Permits;

@Controller
@RequestMapping("/html/product")
public class ProductView {
	@Autowired
	private IProductService productService;
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/list")
	public ModelAndView list(@RequestBody ProductBean product){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("product");
		List<ProductBean> productList=productService.getListByCondition(product);
		Long productSize=productService.getSizeByCondition(product);
		mav.addObject("productList", productList);
		mav.addObject("productTotalCount", productSize);
		return mav;
	}
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/update")
	public ModelAndView update(@RequestBody ProductBean product){
		ModelAndView mav=new ModelAndView();
		String ret=productService.updateProduct(product);
		mav.setViewName("product");
		mav.addObject("updateRet", ret);
		return mav;
	}
	
	@Permits(role = {RoleEnum.PLATFORM}, needLogin = true)
    @PostMapping(path = "/add")
	public ModelAndView add(@RequestBody ProductBean product){
		ModelAndView mav=new ModelAndView();
		String ret=productService.addProduct(product);
		mav.setViewName("product");
		mav.addObject("addRet", ret);
		return mav;
	}
	
}
