package com.mehome.view;

import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.CreateOrderBean;
import com.mehome.enumDTO.TradeType;
import com.mehome.pay.iface.IWeChatService;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/order")
public class WxOrder {
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private IWeChatService weChatService;
    @Autowired
    private IOrderService orderService;

//    @RequestMapping("/payment_create_order")
//    public String payment_create_order(@ModelAttribute CreateOrderBean orderBean, HttpServletRequest request, Model model) {
//        System.out.println("收到表单请求");
//        try {
//            System.out.println(orderBean);
//            String clientIp = request.getHeader("x-real-ip");
//            System.out.println("clientIp : " + clientIp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ThirdPayMentBean bean = new ThirdPayMentBean();
//        bean.setOrderId(orderBean.getOrderId());
//        bean.setTradeType(orderBean.getTradeType());
//        bean.setPayer(orderBean.getPayer());
//        bean.setPayType(orderBean.getPayType());
//        ThirdPayMentBean retBean = orderService.paymentCreateOrder(bean);
//        model.addAttribute("appId", retBean.getAppId());
//        model.addAttribute("timeStamp", retBean.getSeconds());
//        model.addAttribute("nonceStr", retBean.getNonceStr());
//        model.addAttribute("package", retBean.getPackageStr());
//        model.addAttribute("signType", retBean.getSignType());
//        model.addAttribute("paySign", retBean.getPaySign());
//        if ("wechat".equals(orderBean.getTradeType()) && "JSAPI".equals(orderBean.getTradeType())) {
//            //微信支付
//            return "wxpay";
//        } else {
//            //其他支付
//            return "wxpay";
//        }
//    }

    @PostMapping("/test1")
    public String test1(@ModelAttribute CreateOrderBean orderBean, HttpServletRequest request, Model model) {
        System.out.println(orderBean);
        return "wxpay";
    }

    @RequestMapping("/notify")
    @ResponseBody
    public ResponseEntity<JSONObject> callback(@RequestParam(value = "return_code", required = false) String returnCode,
                                               @RequestParam(value = "return_msg", required = false) String returnMsg,
                                               @RequestBody String body) {

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

    @RequestMapping(path = "/wx/pay")
    public ResponseEntity<Result> list() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "http://m.mjiahome.com");
        httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
        String randomStr = RandomUtils.random(16);
        JSONObject orderParam = new JSONObject();
        orderParam.put("appid", weChatProperties.getAppid());
        orderParam.put("mch_id", weChatProperties.getMchid());
        orderParam.put("out_trade_no", System.currentTimeMillis());
        orderParam.put("total_fee", "1");
        orderParam.put("trade_type", TradeType.JSAPI.getName());
        orderParam.put("body", "goods-id");
        orderParam.put("nonce_str", randomStr);
        orderParam.put("spbill_create_ip", "180.173.205.205");
        orderParam.put("openid", "oG8mDwNxCJeM0Ll01x4Eyb1nm6S0");
        orderParam.put("notify_url", "http://api.mjiahome.com//wx/order/notify2");
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
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(Result.build().content(result));
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build());
    }
}
