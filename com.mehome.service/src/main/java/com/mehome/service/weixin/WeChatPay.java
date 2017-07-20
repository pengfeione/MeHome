package com.mehome.service.weixin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IThirdPay;
import com.mehome.utils.DateUtils;
import com.mehome.utils.NetUtils;
import com.mehome.utils.OrderIdUtils;
import com.mehome.utils.PropertiesUtil;
import com.mehome.utils.WeChatJsAPIConfig;
import com.mehome.utils.WeChatProperties;
import com.mehome.utils.WeChatUnifiedOrder;
import com.mehome.utils.XmlUtil;
@Service("wechat")
public class WeChatPay implements IThirdPay {
    RestTemplate restTemplate = new RestTemplate();
    private WXPayConfigImpl config;
    private WXPay wxpay;
    private Logger log = Logger.getLogger(this.getClass());

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
    public ThirdPayMentBean pay(ThirdPayMentBean bean) {
        try {
            if (StringUtils.isBlank(bean.getTradeType())) {
                log.error("tradeType属性未传");
                return null;
            }
            HashMap<String, String> data = new HashMap<String, String>();
//	        data.put("body", "腾讯充值中心-QQ会员充值");
            data.put("body", bean.getBody());
            //data.put("body", order.getProductName());
            data.put("out_trade_no", bean.getOrderId());
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
            //data.put("total_fee", order.getDeposit().toString());
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "121.40.18.88");
            data.put("notify_url", "http://cms.mjiahome.com/api/wechat/notify");
          //  data.put("trade_type", bean.getTradeType());
            data.put("trade_type", "JSAPI");
            if (WXPayConstants.TRADETYPE_JSAPI.equals(bean.getTradeType())) {
                data.put("openid", bean.getOpenId());
            }
            if (WXPayConstants.TRADETYPE_H5.equals(bean.getTradeType())) {
                Map<String, Map> infoMap = new HashMap<String, Map>();
                Map<String, String> h5Map = new HashMap<String, String>();
                h5Map.put("type", "Wap");
                h5Map.put("wap_url", "http://m.mjiahome.com");
                h5Map.put("wap_name", "米家公寓");
                infoMap.put("h5_info", h5Map);
                String sceneInfo = JSON.toJSONString(infoMap);
                data.put("scene_info", sceneInfo);
            }
            Map<String, String> ret = wxpay.unifiedOrder(data);
            log.info("ret:" + ret);
            bean.setPayRet(ret);
            if (ret.containsKey("prepay_id")) {
                String packageStr = "prepay_id=" + ret.get("prepay_id");
                Map<String, String> reqData = new HashMap<String, String>();
                //大写模式
                bean.setAppId(ret.get("appid"));
                reqData.put("appId", bean.getAppId().trim());
                bean.setSeconds(WXPayUtil.getCurrentTimestamp() + "");
                reqData.put("timeStamp", bean.getSeconds().toString());
                bean.setNonceStr(WXPayUtil.generateUUID().trim());
                reqData.put("nonceStr", bean.getNonceStr());
                bean.setPackageStr(packageStr.trim());
                reqData.put("package", bean.getPackageStr());
                String signType = "MD5";
                bean.setSignType(signType);
                //signType为MD5
                reqData.put("signType", signType);
                String secondSign = wxpay.fillRequestDataWithMD5(reqData);
                bean.setPaySign(secondSign);
                if (ret.get("mweb_url") != null)
                    bean.setMwebUrl(ret.get("mweb_url"));
            }
        } catch (Exception e) {
            log.error("微信下单出错:" + e);
        }
        log.info(JSON.toJSON(bean));
        return bean;


    }
}