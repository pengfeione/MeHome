package com.mehome.service.impl;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
@Service("creditcard")
public class CardPay implements IThirdPay {

	@Override
	public ThirdPayMentBean pay(ThirdPayMentBean bean) {
		System.out.println("creditcard");
		return null;
	}

}
