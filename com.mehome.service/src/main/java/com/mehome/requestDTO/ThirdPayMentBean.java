package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;

public class ThirdPayMentBean extends PageMysqlUtil {
	
	private String orderId;
	
	private String payType;
	
	private Integer payStatus;
	
	private String openId;
	
	private Integer amount;
	
	private String callbackUrl;
	
	private String payFlow;

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

	public String getPayFlow() {
		return payFlow;
	}

	public void setPayFlow(String payFlow) {
		this.payFlow = payFlow;
	}
	
	
	
	

}
