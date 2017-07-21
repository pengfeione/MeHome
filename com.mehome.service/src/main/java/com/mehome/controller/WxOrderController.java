package com.mehome.controller;

import com.alibaba.fastjson.JSONObject;
import com.mehome.enumDTO.TradeType;
import com.mehome.pay.iface.IWeChatService;
import com.mehome.utils.RandomUtils;
import com.mehome.utils.SignUtils;
import com.mehome.utils.WeChatApiProperties;
import com.mehome.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by trancy on 2017/7/20.
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private IWeChatService weChatService;

    @RequestMapping("/callback")
    @ResponseBody
    public ResponseEntity<JSONObject> callback(@RequestParam(value = "return_code", required = false) String returnCode,
                                               @RequestParam(value = "return_msg", required = false) String returnMsg,
                                               @RequestBody String body
    ) {
        String str = "";
        try {
            System.out.println(body);
            str = new String(body.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(XmlUtils.toJSON(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(XmlUtils.toJSON(body));
        } finally {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(new JSONObject());
        }
    }

    @GetMapping("/make")
    @ResponseBody
    public ResponseEntity<JSONObject> order() {
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
            result.put("paySign", SignUtils.sign(result, weChatProperties.getKey()));
            System.out.println(result.toJSONString());
            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(result);
        } else {
            JSONObject result = new JSONObject();
            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(weChatService.makeOrder(result));
        }
    }
}
