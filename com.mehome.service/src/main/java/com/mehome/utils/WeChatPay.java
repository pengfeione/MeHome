package com.mehome.utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
public class WeChatPay {

	@Autowired

	public  WeChatUnifiedOrder weChatUnifiedOrder;

	private String appId =weChatUnifiedOrder.getAppid();

	private  String mchId=weChatUnifiedOrder.getMch_id();
	private  String payKey;
	/**
	 * 统一下单
	 * @Title: unifiedOrder
	 * @Description: TODO
	 * @param: @param openId 微信用户openId
	 * @param: @param orderId 订单ID
	 * @param: @param money 订单总价，单位：分
	 * @param: @param callbackUrl 回调路径
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 */
	public String unifiedOrder(String openId,String orderId,int money,String callbackUrl) throws Exception{
		WeChatUnifiedOrder unifiedOrder = new WeChatUnifiedOrder();
		unifiedOrder.setAppid(appId);
		unifiedOrder.setAttach("hehedesk");

		unifiedOrder.setBody("hehedesk");
		unifiedOrder.setMch_id(mchId);

		String nonce = UUID.randomUUID().toString().substring(0, 30);
		unifiedOrder.setNonce_str(nonce);
		unifiedOrder.setNotify_url(callbackUrl);

		unifiedOrder.setOpenid(openId);
		unifiedOrder.setOut_trade_no(orderId);

		unifiedOrder.setSpbill_create_ip("14.23.150.211");
		unifiedOrder.setTotal_fee(money);

		String sign = createUnifiedOrderSign(unifiedOrder);
		unifiedOrder.setSign(sign);

//		/**
//		 * 转成XML格式
//		 */
//		xmlUtil.getXstreamInclueUnderline().alias("xml", unifiedOrder.getClass());
//		String xml = xmlUtil.getXstreamInclueUnderline().toXML(unifiedOrder);
//
//		String response = httpConnection.post(unifiedOrderUrl, xml);
//		logger.info("unifiedOrder");
//		logger.info(response);
//		Map<String, String> responseMap = xmlUtil.parseXml(response);
//
//		return responseMap.get("prepay_id");
		return  "";
	}
	/**
	 * 获取统一下单签名
	 * @Title: createUnifiedOrderSign
	 * @Description: TODO
	 * @param @param unifiedOrder
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String createOrderSign(WeChatUnifiedOrder unifiedOrder){
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
	 * @Title: createUnifiedOrderSign
	 * @Description: TODO
	 * @param @param unifiedOrder
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String createUnifiedOrderSign(WeChatUnifiedOrder unifiedOrder) throws Exception {
		StringBuffer sign = new StringBuffer();
		Map<String, String> map = getSortMap(unifiedOrder);

		boolean isNotFirst = false;

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if(isNotFirst == true){
				sign.append("&");
			}else{
				isNotFirst = true;
			}

			sign.append(entry.getKey()).append("=").append(entry.getValue());
		}
		sign.append("&key=").append(payKey);

		return DigestUtils.md5Hex(sign.toString()).toUpperCase();

	}


	/**
	 * 获取排序后的类属性及值
	 * @param object
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getSortMap(Object object) throws Exception{
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String, String> map = new HashMap<String, String>();

		for(Field field : fields){
			String name = field.getName();
			String methodName = "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
					.toUpperCase());
			Method getter = object.getClass().getMethod(methodName);
			// 调用getter方法获取属性值
			String value =  getter.invoke(object)+"";
			if (value != null){
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
	/**
	 * 获取支付配置
	 * @Title: createPayConfig
	 * @Description: TODO
	 * @param @param preayId 统一下单prepay_id
	 * @param @return
	 * @param @throws Exception
	 * @return JsAPIConfig
	 * @throws
	 */
	public WeChatJsAPIConfig  createPayConfig(String prepayId) throws Exception {
		WeChatJsAPIConfig config = new WeChatJsAPIConfig();

		String nonce = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String packageName = "prepay_id="+prepayId;
		StringBuffer sign = new StringBuffer();
		sign.append("appId=").append(appId);
		sign.append("&nonceStr=").append(nonce);
		sign.append("&package=").append(packageName);
		sign.append("&signType=").append(config.getSignType());
		sign.append("&timeStamp=").append(timestamp);
		sign.append("&key=").append(payKey);
		String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();

		config.setAppId(appId);
		config.setNonce(nonce);
		config.setTimestamp(timestamp);
		config.setPackageName(packageName);
		config.setSignature(signature);

		return config;
	}
}
