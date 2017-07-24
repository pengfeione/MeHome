package com.mehome.service.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mehome.enumDTO.TradeType;
import com.mehome.pay.iface.IWeChatService;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
import com.mehome.utils.PropertiesUtil;
import com.mehome.utils.RandomUtils;
import com.mehome.utils.SignUtils;
import com.mehome.utils.WeChatApiProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

@Service("wechat")
public class WeChatPay implements IThirdPay {
    RestTemplate restTemplate = new RestTemplate();
    private WXPayConfigImpl config;
    private WXPay wxpay;
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private IWeChatService weChatService;
    @Autowired
    private PropertiesUtil propertiesUtil;
    //    private String appId = weChatUnifiedOrder.getAppid();
//    private String unifiedOrderUrl = weChatProperties.getPay_unifiedorder();
    private String payKey;

    //    private String mchId = weChatUnifiedOrder.getMch_id();
    @PostConstruct
    public void init() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
    }

    @Override
    public JSONObject pay(ThirdPayMentBean bean) {
    	JSONObject result = new JSONObject();
    	try {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "http://m.mjiahome.com");
        httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
//        String randomStr = RandomUtils.random(16);
        JSONObject orderParam = new JSONObject();
        orderParam.put("appid", config.getAppID());
        orderParam.put("mch_id", config.getMchID());
        orderParam.put("out_trade_no", bean.getOrderId());
        orderParam.put("total_fee", 1);
        orderParam.put("trade_type", bean.getTradeType());
        orderParam.put("body", "me"+bean.getOrderId());
        orderParam.put("nonce_str", WXPayUtil.generateUUID());
        orderParam.put("spbill_create_ip", "121.40.18.88");
        if (WXPayConstants.TRADETYPE_JSAPI.equals(bean.getTradeType())) {
        	orderParam.put("openid", bean.getOpenId());
      	}
        if (WXPayConstants.TRADETYPE_H5.equals(bean.getTradeType())) {
          Map<String, Map> infoMap = new HashMap<String, Map>();
          Map<String, String> h5Map = new HashMap<String, String>();
          h5Map.put("type", "Wap");
          h5Map.put("wap_url", "http://m.mjiahome.com");
          h5Map.put("wap_name", "miahome");
          infoMap.put("h5_info", h5Map);
          String sceneInfo = JSON.toJSONString(infoMap);
          orderParam.put("scene_info", sceneInfo);
      }
//        orderParam.put("openid", "oG8mDwNxCJeM0Ll01x4Eyb1nm6S0");
        //http://api.mjiahome.com//api/wechat/notify   http://api.mjiahome.com//wx/order/notify2
        orderParam.put("notify_url", "http://api.mjiahome.com//wx/order/notify");
        orderParam.put("sign", SignUtils.sign(orderParam, config.getKey()));
        JSONObject orderResult = weChatService.makeOrder(orderParam);
        if ("SUCCESS".equals(orderResult.getString("return_code"))) {
        	System.out.println("统一下单正常,orderResult:"+orderResult.toJSONString());
            result.put("appId", config.getAppID());
            result.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
            result.put("nonceStr", WXPayUtil.generateUUID());
            result.put("package", "prepay_id=" + orderResult.getString("prepay_id"));
            result.put("signType", "MD5");
            result.put("paySign", SignUtils.sign(result, config.getKey()));
        }else{
        	String error=orderResult.getString("return_msg");
        	log.error("统一下单报错:"+error);
        	System.out.println("统一下单报错:"+error);
        }
//            if (StringUtils.isBlank(bean.getTradeType())) {
//                log.error("tradeType属性未传");
//                return null;
//            }
//            HashMap<String, String> data = new HashMap<String, String>();
////	        data.put("body", "腾讯充值中心-QQ会员充值");
//            data.put("body", bean.getBody());
//            //data.put("body", order.getProductName());
//            data.put("out_trade_no", bean.getOrderId());
//            data.put("device_info", "WEB");
//            data.put("fee_type", "CNY");
//            //data.put("total_fee", order.getDeposit().toString());
//            data.put("total_fee", "1");
//            data.put("spbill_create_ip", "121.40.18.88");
//            data.put("notify_url", "http://api.mjiahome.com//wx/order/notify");
//            //  data.put("trade_type", bean.getTradeType());
//            data.put("trade_type", "JSAPI");
//            if (WXPayConstants.TRADETYPE_JSAPI.equals(bean.getTradeType())) {
//                data.put("openid", bean.getOpenId());
//            }
//            if (WXPayConstants.TRADETYPE_H5.equals(bean.getTradeType())) {
//                Map<String, Map> infoMap = new HashMap<String, Map>();
//                Map<String, String> h5Map = new HashMap<String, String>();
//                h5Map.put("type", "Wap");
//                h5Map.put("wap_url", "http://m.mjiahome.com");
//                h5Map.put("wap_name", "miahome");
//                infoMap.put("h5_info", h5Map);
//                String sceneInfo = JSON.toJSONString(infoMap);
//                data.put("scene_info", sceneInfo);
//            }
//            Map<String, String> ret = wxpay.unifiedOrder(data);
//
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.add("Access-Control-Allow-Origin", "http://m.mjiahome.com");
//            httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
//            String randomStr = RandomUtils.random(16);
//            JSONObject orderParam = new JSONObject();
//            orderParam.put("appid", weChatProperties.getAppid());
//            orderParam.put("mch_id", weChatProperties.getMchid());
//            orderParam.put("out_trade_no", System.currentTimeMillis());
//            orderParam.put("total_fee", "100");
//            orderParam.put("trade_type", TradeType.JSAPI.getName());
//            orderParam.put("body", "goods-id");
//            orderParam.put("nonce_str", randomStr);
//            orderParam.put("spbill_create_ip", "180.173.205.205");
//            orderParam.put("openid", "oG8mDwNxCJeM0Ll01x4Eyb1nm6S0");
//            orderParam.put("notify_url", "http://api.mjiahome.com//wx/order/notify");
//            orderParam.put("sign", SignUtils.sign(orderParam, weChatProperties.getKey()));
//            JSONObject orderResult = weChatService.makeOrder(orderParam);
//            if ("SUCCESS".equals(orderResult.getString("return_code"))) {
//
//
//                log.info("ret:" + ret);
//                bean.setPayRet(ret);
//                if (ret.containsKey("prepay_id")) {
//                    String packageStr = "prepay_id=" + ret.get("prepay_id");
//                    Map<String, String> reqData = new HashMap<String, String>();
//                    //大写模式
//                    bean.setAppId(ret.get("appid"));
//                    reqData.put("appId", bean.getAppId().trim());
//                    bean.setSeconds(WXPayUtil.getCurrentTimestamp() + "");
//                    reqData.put("timeStamp", bean.getSeconds().toString());
////                bean.setNonceStr(WXPayUtil.generateUUID().trim());
//                    bean.setNonceStr(ret.get("nonce_str"));
//                    reqData.put("nonceStr", bean.getNonceStr());
//                    bean.setPackageStr(packageStr.trim());
//                    reqData.put("package", bean.getPackageStr());
//                    String signType = "MD5";
//                    bean.setSignType(signType);
//                    //signType为MD5
//                    reqData.put("signType", signType);
//                    String secondSign = wxpay.fillRequestDataWithMD5(reqData);
//
//
//                    result.put("appId", weChatProperties.getAppid());
//                    result.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//                    result.put("nonceStr", bean.getNonceStr());
//                    result.put("package", "prepay_id=" + ret.get("prepay_id"));
//                    result.put("signType", "MD5");
//                    result.put("paySign", SignUtils.sign(result, weChatProperties.getKey()));
//                    System.out.println(result.toJSONString());
//                    bean.setPaySign(secondSign);
//                    if (ret.get("mweb_url") != null)
//                        bean.setMwebUrl(ret.get("mweb_url"));
//                }
        } catch (Exception e) {
            log.error("微信下单出错:" + e);
        }
//            log.info(JSON.toJSON(bean));
        return result;
    }
}