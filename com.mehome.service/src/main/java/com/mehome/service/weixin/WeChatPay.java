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
    public void init() throws Exception{
    	config = WXPayConfigImpl.getInstance();
    	wxpay=new WXPay(config);
    }
	@Override
	public ThirdPayMentBean pay(ThirdPayMentBean bean) {
		try {
			if(StringUtils.isBlank(bean.getTradeType())){
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
	        data.put("notify_url", "http://m.mjiahome.com/api/wechat/notify");
	        data.put("trade_type", bean.getTradeType());
//	        data.put("trade_type", "JSAPI");
	        if(WXPayConstants.TRADETYPE_JSAPI.equals(bean.getTradeType())){
	        data.put("openid",bean.getOpenId());
	        }
	        if(WXPayConstants.TRADETYPE_H5.equals(bean.getTradeType())){
//	        	3，WAP网站应用
//	        	{"h5_info": //h5支付固定传"h5_info" 
//	        	   {"type": "",  //场景类型
//	        	    "wap_url": "",//WAP网站URL地址
//	        	    "wap_name": ""  //WAP 网站名
//	        	    }
//	        	}
	        	Map<String,Map> infoMap=new HashMap<String,Map>();
	        	Map<String,String> h5Map=new HashMap<String,String>();
	        	h5Map.put("type", "Wap");
	        	h5Map.put("wap_url", "http://m.mjiahome.com");
	        	h5Map.put("wap_name", "米家公寓");
	        	infoMap.put("h5_info", h5Map);
	        	String sceneInfo=JSON.toJSONString(infoMap);
	        	data.put("scene_info", sceneInfo);
	        }
	        Map<String, String> ret = wxpay.unifiedOrder(data);
	        log.info("ret:"+ret);
	        bean.setPayRet(ret);
	        
//	        "appId":data.appId,     //公众号名称，由商户传入     "wx966efd886c5be652"
//	           "timeStamp":data.seconds,         //时间戳，自1970年以来的秒数     
//	           "nonceStr":data.nonceStr, //随机串     "e61463f8efa94090b1f366cccfbbb444"
//	           "package":data.packageStr, //prepay_id=u802345jgfjsdfgsdg888    
//	           "signType":"MD5",         //微信签名方式：     
//	           "paySign":data.paySign //微信签名 "70EA570631E4BB79628FBCA90534C63FF7FADD89"
	        if(ret.containsKey("prepay_id")){
	        	String packageStr="prepay_id="+ret.get("prepay_id");
	        	Map<String,String> reqData=new HashMap<String,String>();
	        	bean.setAppId(propertiesUtil.getAppid());
	        	reqData.put("appId", bean.getAppId());
	        	bean.setSeconds(WXPayUtil.getCurrentTimestamp()+"");
	        	reqData.put("timeStamp", bean.getSeconds().toString());
	        	bean.setNonceStr(WXPayUtil.generateUUID());
	        	reqData.put("nonceStr", bean.getNonceStr());
	        	bean.setPackageStr(packageStr);
	        	reqData.put("package", bean.getPackageStr());
	        	//signType为MD5
	        	reqData.put("signType", "MD5");
	        	String Sign =wxpay.fillRequestDataWithMD5(reqData);
	        	log.info("paySign:"+Sign);
	        	bean.setPaySign(Sign);
	        	if(ret.get("mweb_url")!=null)
	        	bean.setMwebUrl(ret.get("mweb_url"));
	        }
		} catch (Exception e) {
			log.error("微信下单出错:"+e);
		}
		return bean;



	}
//	 /**
//     * 统一下单
//     *
//     * @Title: unifiedOrder
//     * @Description: TODO
//     * @param: @param openId 微信用户openId
//     * @param: @param orderId 订单ID
//     * @param: @param money 订单总价，单位：分
//     * @param: @param callbackUrl 回调路径
//     * @param: @return
//     * @param: @throws Exception
//     * @return: String
//     */
//    public String unifiedOrder(String openId, String orderId, int money, String callbackUrl) throws Exception {
//        WeChatUnifiedOrder unifiedOrder = new WeChatUnifiedOrder();
//        unifiedOrder.setAppid(weChatUnifiedOrder.getAppid());
//        unifiedOrder.setAttach("hehedesk");
//
//        unifiedOrder.setBody("hehedesk");
//        unifiedOrder.setMch_id(weChatUnifiedOrder.getMch_id());
//
//        String nonce = UUID.randomUUID().toString().substring(0, 30);
//        unifiedOrder.setNonce_str(nonce);
//        unifiedOrder.setNotify_url(callbackUrl);
//
//        unifiedOrder.setOpenid(openId);
//        unifiedOrder.setOut_trade_no(orderId);
//
//        unifiedOrder.setSpbill_create_ip("14.23.150.211");
//        unifiedOrder.setTotal_fee(money);
//
//        String sign = createUnifiedOrderSign(unifiedOrder);
//        unifiedOrder.setSign(sign);
//        /**
//         * 转成XML格式
//         */
//        String xml = XmlUtil.toXml(unifiedOrder);
//        Map<String, String> responseMap = restTemplate.getForObject(weChatProperties.getPay_unifiedorder(), Map.class);
//        return responseMap.get("prepay_id");
//    }

    /**
     * 获取统一下单签名
     *
     * @param @param  unifiedOrder
     * @param @return
     * @return String
     * @throws
     * @Title: createUnifiedOrderSign
     * @Description: TODO
     */
    public String createOrderSign(WeChatUnifiedOrder unifiedOrder) {
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(unifiedOrder.getAppid());
        sign.append("&attach=").append(unifiedOrder.getAttach());
        sign.append("&body=").append(unifiedOrder.getBody());
        sign.append("&device_info=").append(unifiedOrder.getDevice_info());
        sign.append("&mch_id=").append(unifiedOrder.getMch_id());
        sign.append("&nonce_str=").append(unifiedOrder.getNonce_str());
        sign.append("&notify_url=").append(unifiedOrder.getNotify_url());
        sign.append("&openid=").append(unifiedOrder.getOpenid());
        sign.append("&out_trade_no=").append(unifiedOrder.getOut_trade_no());
        sign.append("&spbill_create_ip=").append(unifiedOrder.getSpbill_create_ip());
        sign.append("&total_fee=").append(unifiedOrder.getTotal_fee());
        sign.append("&trade_type=").append(unifiedOrder.getTrade_type());
        sign.append("&key=").append(payKey);

        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }

    /**
     * 获取统一下单签名
     *
     * @param @param  unifiedOrder
     * @param @return
     * @return String
     * @throws
     * @Title: createUnifiedOrderSign
     * @Description: TODO
     */
    public String createUnifiedOrderSign(WeChatUnifiedOrder unifiedOrder) throws Exception {
        StringBuffer sign = new StringBuffer();
        Map<String, String> map = getSortMap(unifiedOrder);

        boolean isNotFirst = false;

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (isNotFirst == true) {
                sign.append("&");
            } else {
                isNotFirst = true;
            }

            sign.append(entry.getKey()).append("=").append(entry.getValue());
        }
        sign.append("&key=").append(payKey);

        return DigestUtils.md5Hex(sign.toString()).toUpperCase();

    }


    /**
     * 获取排序后的类属性及值
     *
     * @param object
     * @return
     * @throws Exception
     */
    private Map<String, String> getSortMap(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>();

        for (Field field : fields) {
            String name = field.getName();
            String methodName = "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                    .toUpperCase());
            Method getter = object.getClass().getMethod(methodName);
            // 调用getter方法获取属性值
            String value = getter.invoke(object) + "";
            if (value != null) {
                map.put(name, value);
            }
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {

                    @Override
                    public int compare(String arg0, String arg1) {

                        return arg0.compareTo(arg1);
                    }

                });

        sortMap.putAll(map);

        return sortMap;
    }

//    /**
//     * 获取支付配置
//     *
//     * @param @param  preayId 统一下单prepay_id
//     * @param @return
//     * @param @throws Exception
//     * @return JsAPIConfig
//     * @throws
//     * @Title: createPayConfig
//     * @Description: TODO
//     */
//    public WeChatJsAPIConfig createPayConfig(String prepayId) throws Exception {
//        WeChatJsAPIConfig config = new WeChatJsAPIConfig();
//
//        String nonce = UUID.randomUUID().toString();
//        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//        String packageName = "prepay_id=" + prepayId;
//        StringBuffer sign = new StringBuffer();
//        sign.append("appId=").append(weChatUnifiedOrder.getAppid());
//        sign.append("&nonceStr=").append(nonce);
//        sign.append("&package=").append(packageName);
//        sign.append("&signType=").append(config.getSignType());
//        sign.append("&timeStamp=").append(timestamp);
//        sign.append("&key=").append(payKey);
//        String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();
//
//        config.setAppId(weChatUnifiedOrder.getAppid());
//        config.setNonce(nonce);
//        config.setTimestamp(timestamp);
//        config.setPackageName(packageName);
//        config.setSignature(signature);
//
//        return config;
//    }
}
