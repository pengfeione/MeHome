package com.mehome.pay.iface;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by trancy on 2017/7/20.
 */
public interface IWeChatService {
    /**
     * WeChatOrder.class
     *
     * @param order
     * @return
     */
    JSONObject makeOrder(JSONObject order);
}
