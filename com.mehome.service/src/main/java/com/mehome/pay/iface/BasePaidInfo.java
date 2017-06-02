package com.mehome.pay.iface;

public abstract class BasePaidInfo<NotifyInfo extends INotifyInfo<PayChannel>, PayChannel extends IPayChannel> {
	private String payChannelSeq;
	private long tradeTime;
	private String paymentId;
	protected BasePaidInfo(String paymentId, long tradeTime, String payChannelSeq) {
		this.paymentId = paymentId;
		this.tradeTime = tradeTime;
		this.payChannelSeq = payChannelSeq;
	}
	public String getPayChannelSeq() {
		return payChannelSeq;
	}
	public long getTradeTime() {
		return tradeTime;
	}
	public String getPaymentId() {
		return paymentId;
	}
	abstract public String getPayChannelName();
}
