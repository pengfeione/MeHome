package com.mehome.service.iface;

import com.alibaba.fastjson.JSONObject;
import com.mehome.requestDTO.ThirdPayMentBean;

public interface IThirdPay {

    public JSONObject pay(ThirdPayMentBean bean);
}
