package com.mehome.requestDTO;

import java.util.Date;
import java.util.Map;

import com.mehome.domain.OrderList;
import com.mehome.domain.ThirdPartyPayment;
import com.mehome.utils.PageMysqlUtil;

public class ThirdPayMentBean extends PageMysqlUtil {
	
	private String orderId;
	
	private String payType;
	
	private Integer payStatus;
	
	private String openId;
	
	private Integer amount;
	
	private String callbackUrl;
	
	private String tradeSeq;
	
	private String nonceStr;
	
	private String packageStr;
	
	private String paySign;
	
	private String payer;
	
	private Long seconds;
	
	private Map<String,String> payRet;
	
	private String tradeType;
	
	private String body;
	
	private String appId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Map<String, String> getPayRet() {
		return payRet;
	}

	public void setPayRet(Map<String, String> payRet) {
		this.payRet = payRet;
	}

	public Long getSeconds() {
		return seconds;
	}

	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ThirdPartyPayment beanToPojo() throws Exception{
		ThirdPartyPayment payMent=new ThirdPartyPayment();
		Date date=new Date();
		payMent.setOrderId(this.getOrderId());
		payMent.setPayAccount(this.getPayer());
		payMent.setPayAmount(this.getAmount());
		payMent.setPayBody("");
		payMent.setPayStatus(this.getPayStatus());
		payMent.setPayTime(date);
		payMent.setCallbackUrl(this.getCallbackUrl());
		payMent.setPayType(this.getPayType());
		payMent.setPayUid(Integer.parseInt(this.getPayer()));
		payMent.setTradeSeq(this.getTradeSeq());
		payMent.setUpdateTime(date);
		return payMent;
		
	}
	public ThirdPayMentBean(OrderList order){
		this.setAmount(order.getDeposit());
		this.setBody(order.getProductName());
		this.setOrderId(order.getOrderId());
//		this.set
	}
	public ThirdPayMentBean(){
		
	}
	

}
