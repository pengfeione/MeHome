package com.mehome.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.alibaba.fastjson.JSONObject;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.AlipayProperties;
@WebServlet(urlPatterns = "/api/order/alipay_create_order", description = "Servlet的说明,使用jsonp协议", name = "微信JS-SDK分享公共接口")
public class AliPayServlet extends HttpServlet {
	@Autowired
    private IOrderService orderService;
    @Autowired
	private AlipayProperties alipayProperties;
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	ThirdPayMentBean bean=new ThirdPayMentBean();
    	//req.getParameter("orderId");
    	String orderId="201707251736151087652137";
    	//req.getParameter("payer");
    	String payer="10056";
    	bean.setOrderId(orderId);
    	bean.setPayer(payer);
    	//req.getParameter("payType");
    	bean.setPayType("alipay");
    	JSONObject ret=orderService.paymentCreateOrder(bean);
    	String form=ret.getString("ret");
    	resp.setContentType("text/html;charset=" + alipayProperties.getCharset()); 
	    try {
	    	//直接将完整的表单html输出到页面 
	    	PrintWriter print=resp.getWriter();
	    	print.write(form);
	    	print.flush(); 
	    	print.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
