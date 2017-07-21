package com.mehome.view;

import com.alibaba.fastjson.JSONObject;
import com.mehome.enumDTO.TradeType;
import com.mehome.pay.iface.IWeChatService;
import com.mehome.utils.RandomUtils;
import com.mehome.utils.SignUtils;
import com.mehome.utils.WeChatApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/order")
public class WxOrder {
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private IWeChatService weChatService;

    @GetMapping(path = "/wx/pay")
    public String list(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "http://m.mjiahome.com");
        httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
        String randomStr = RandomUtils.random(16);
        JSONObject orderParam = new JSONObject();
        orderParam.put("appid", weChatProperties.getAppid());
        orderParam.put("mch_id", weChatProperties.getMchid());
        orderParam.put("out_trade_no", System.currentTimeMillis());
        orderParam.put("total_fee", "100");
        orderParam.put("trade_type", TradeType.JSAPI.getName());
        orderParam.put("body", "goods-id");
        orderParam.put("nonce_str", randomStr);
        orderParam.put("spbill_create_ip", "180.173.205.205");
        orderParam.put("openid", "oG8mDwNxCJeM0Ll01x4Eyb1nm6S0");
        orderParam.put("notify_url", "http://api.mjiahome.com//wx/order/callback");
        orderParam.put("sign", SignUtils.sign(orderParam, weChatProperties.getKey()));
        JSONObject orderResult = weChatService.makeOrder(orderParam);
        if ("SUCCESS".equals(orderResult.getString("return_code"))) {
            JSONObject result = new JSONObject();
            result.put("appId", weChatProperties.getAppid());
            result.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            result.put("nonceStr", randomStr);
            result.put("package", "prepay_id=" + orderResult.getString("prepay_id"));
            result.put("signType", "MD5");
            model.addAttribute("appId", weChatProperties.getAppid());
            model.addAttribute("timeStamp", System.currentTimeMillis() / 1000 + "");
            model.addAttribute("nonceStr", randomStr);
            model.addAttribute("package", "prepay_id=" + orderResult.getString("prepay_id"));
            model.addAttribute("signType", "MD5");
            model.addAttribute("paySign", SignUtils.sign(result, weChatProperties.getKey()));
        }
        return "wxpay";
    }
}
