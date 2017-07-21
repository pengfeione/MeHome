package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
import org.springframework.stereotype.Service;

@Service("creditcard")
public class CardPay implements IThirdPay {

    @Override
    public JSONObject pay(ThirdPayMentBean bean) {
        System.out.println(" creditcard");
        return null;
    }

}
