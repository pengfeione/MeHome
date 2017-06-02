package com.mehome.pay.iface;

public interface IPayChannel {
	public String getChannelName();
	public void setConnector(IPaymentServiceConnector connector);
}
