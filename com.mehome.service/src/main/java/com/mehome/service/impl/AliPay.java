package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.*;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
import com.mehome.utils.AlipayProperties;
import com.mehome.utils.WeChatApiProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("alipay")
public class AliPay implements IThirdPay {
	@Autowired
	private AlipayProperties alipayProperties;

	@Override
	public JSONObject pay(ThirdPayMentBean bean) {
		JSONObject jsonObject = new JSONObject();
		try {
			System.out.println("alipay");
			String retStr = gatewayDo(bean);
			System.out.println("form:"+retStr);
			jsonObject.put("ret", retStr);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			System.out.println("alipay error:"+e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("io error:"+e);
			e.printStackTrace();
		}
		return jsonObject;
	}

	public String gatewayDo(ThirdPayMentBean bean) throws AlipayApiException, IOException {
		// AlipayClient alipayClient = new
		// AlipayMobilePublicMultiMediaClient(serverUrl, appId, privateKey);
		// AlipayClient alipayClientA = new DefaultAlipayClient(serverUrl,
		// appId, privateKey);
		AlipayClient alipayClientA = new DefaultAlipayClient(alipayProperties.getServeurl(),
				alipayProperties.getAppid(), alipayProperties.getPrivatekey(), "json", "UTF-8",
				alipayProperties.getPublickey(), "RSA2");
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		// 封装请求支付信息
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(bean.getOrderId());
		model.setSubject("me公寓支付");
//		Integer amount=bean.getAmount();
//		Float f=(float) (amount*0.01);
//		model.setTotalAmount(f.toString());
		model.setTotalAmount("0.01");
		model.setBody("me公寓支付");
		model.setTimeoutExpress(alipayProperties.getTimeout());
		model.setProductCode(alipayProperties.getProductcode());
		alipayRequest.setBizModel(model);
		alipayRequest.setReturnUrl(alipayProperties.getReturnurl());
		alipayRequest.setNotifyUrl(alipayProperties.getNotifyurl());// 在公共参数中设置回跳和通知地址
		// alipayRequest.setBizContent("{" +
		// " \"out_trade_no\":\"20150320010101002\"," +
		// " \"total_amount\":88.88," +
		// " \"subject\":\"Iphone6 16G\"," +
		// " \"seller_id\":\"2088123456789012\"," +
		// " \"product_code\":\"QUICK_WAP_PAY\"" +
		// " }");//填充业务参数
		String form = alipayClientA.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		// response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
		// response.getWriter().write(form);//直接将完整的表单html输出到页面
		// response.getWriter().flush();
		// response.getWriter().close();
		return form;
	}
}
