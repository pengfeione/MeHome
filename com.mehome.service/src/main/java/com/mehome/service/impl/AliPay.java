package com.mehome.service.impl;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
@Service("alipay")
public class AliPay implements IThirdPay {

	@Override
	public String pay(ThirdPayMentBean bean) {
		System.out.println("alipay");
		return null;
	}

}
