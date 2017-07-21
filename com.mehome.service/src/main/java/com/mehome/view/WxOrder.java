package com.mehome.view;

import com.alibaba.fastjson.JSONObject;
import com.mehome.enumDTO.TradeType;
import com.mehome.pay.iface.IWeChatService;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.RandomUtils;
import com.mehome.utils.Result;
import com.mehome.utils.SignUtils;
import com.mehome.utils.WeChatApiProperties;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/order")
public class WxOrder {
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private IWeChatService weChatService;
    @Autowired
    private IOrderService orderService;

    @PostMapping("/payment_create_order" )
    public String payment_create_order(HttpServletRequest request,Model model) {
    	//var param = '{"orderId":' + orderId + ',"tradeType":"JSAPI","payer":' + uid + ',"payType":"wechat"}';
    	System.out.println("收到表单请求");
    	ThirdPayMentBean bean=new ThirdPayMentBean();
    	bean.setOrderId(request.getParameter("orderId"));
    	bean.setTradeType(request.getParameter("tradeType"));
    	bean.setPayer(request.getParameter("payer"));
    	bean.setPayType(request.getParameter("payType"));
    	ThirdPayMentBean retBean=orderService.paymentCreateOrder(bean);
    	model.addAttribute("appId", retBean.getAppId());
        model.addAttribute("timeStamp", retBean.getSeconds());
        model.addAttribute("nonceStr", retBean.getNonceStr());
        model.addAttribute("package", retBean.getPackageStr());
        model.addAttribute("signType",retBean.getSignType());
        model.addAttribute("paySign", retBean.getPaySign());
        return "wxpay";
    }
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
