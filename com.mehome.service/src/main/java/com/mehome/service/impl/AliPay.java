package com.mehome.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.AlipayMobilePublicMultiMediaClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
@Service("alipay")
public class AliPay implements IThirdPay {

	@Override
	public ThirdPayMentBean pay(ThirdPayMentBean bean) {
		System.out.println("alipay");
		return null;
	}
	
	
	public String gatewayDo() throws AlipayApiException, IOException{
		HttpServletResponse httpResponse=null;
		String serverUrl="";
		String appId="";
		String privateKey="";
		AlipayClient alipayClient = new AlipayMobilePublicMultiMediaClient(serverUrl, appId, privateKey);
		AlipayClient alipayClientA =  new DefaultAlipayClient(serverUrl, appId, privateKey);
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
	    alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
	    alipayRequest.setBizContent("{" +
	        "    \"out_trade_no\":\"20150320010101002\"," +
	        "    \"total_amount\":88.88," +
	        "    \"subject\":\"Iphone6 16G\"," +
	        "    \"seller_id\":\"2088123456789012\"," +
	        "    \"product_code\":\"QUICK_WAP_PAY\"" +
	        "  }");//填充业务参数
	    String form = alipayClientA.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    httpResponse.setContentType("text/html;charset=" + AlipayConstants.CHARSET);
	    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
	    httpResponse.getWriter().flush();
		return null;
		
	}

}
