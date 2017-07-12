package com.mehome.domain;

import java.util.Date;

public class ThirdPartyPayment {
    private String paymentId;

    private String orderId;

    private Integer payUid;

    private String payAccount;

    private String receiveAccount;

    private String payType;

    private Integer payStatus;

    private Integer payAmount;

    private String payReason;

    private Date payTime;

    private String payBody;

    private String payBodyId;

    private String callbackUrl;

    private String tradeSeq;

    private String payContent;
    
    private Date updateTime;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getPayUid() {
        return payUid;
    }

    public void setPayUid(Integer payUid) {
        this.payUid = payUid;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount == null ? null : receiveAccount.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayReason() {
        return payReason;
    }

    public void setPayReason(String payReason) {
        this.payReason = payReason == null ? null : payReason.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayBody() {
        return payBody;
    }

    public void setPayBody(String payBody) {
        this.payBody = payBody == null ? null : payBody.trim();
    }

    public String getPayBodyId() {
        return payBodyId;
    }

    public void setPayBodyId(String payBodyId) {
        this.payBodyId = payBodyId == null ? null : payBodyId.trim();
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    public String getTradeSeq() {
        return tradeSeq;
    }

    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq == null ? null : tradeSeq.trim();
    }

    public String getPayContent() {
        return payContent;
    }

    public void setPayContent(String payContent) {
        this.payContent = payContent == null ? null : payContent.trim();
    }

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ThirdPartyPayment other = (ThirdPartyPayment) that;
        return (this.getPaymentId() == null ? other.getPaymentId() == null : this.getPaymentId().equals(other.getPaymentId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getPayUid() == null ? other.getPayUid() == null : this.getPayUid().equals(other.getPayUid()))
            && (this.getPayAccount() == null ? other.getPayAccount() == null : this.getPayAccount().equals(other.getPayAccount()))
            && (this.getReceiveAccount() == null ? other.getReceiveAccount() == null : this.getReceiveAccount().equals(other.getReceiveAccount()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getPayReason() == null ? other.getPayReason() == null : this.getPayReason().equals(other.getPayReason()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getPayBody() == null ? other.getPayBody() == null : this.getPayBody().equals(other.getPayBody()))
            && (this.getPayBodyId() == null ? other.getPayBodyId() == null : this.getPayBodyId().equals(other.getPayBodyId()))
            && (this.getCallbackUrl() == null ? other.getCallbackUrl() == null : this.getCallbackUrl().equals(other.getCallbackUrl()))
            && (this.getTradeSeq() == null ? other.getTradeSeq() == null : this.getTradeSeq().equals(other.getTradeSeq()))
            && (this.getPayContent() == null ? other.getPayContent() == null : this.getPayContent().equals(other.getPayContent()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPaymentId() == null) ? 0 : getPaymentId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getPayUid() == null) ? 0 : getPayUid().hashCode());
        result = prime * result + ((getPayAccount() == null) ? 0 : getPayAccount().hashCode());
        result = prime * result + ((getReceiveAccount() == null) ? 0 : getReceiveAccount().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getPayReason() == null) ? 0 : getPayReason().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getPayBody() == null) ? 0 : getPayBody().hashCode());
        result = prime * result + ((getPayBodyId() == null) ? 0 : getPayBodyId().hashCode());
        result = prime * result + ((getCallbackUrl() == null) ? 0 : getCallbackUrl().hashCode());
        result = prime * result + ((getTradeSeq() == null) ? 0 : getTradeSeq().hashCode());
        result = prime * result + ((getPayContent() == null) ? 0 : getPayContent().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}