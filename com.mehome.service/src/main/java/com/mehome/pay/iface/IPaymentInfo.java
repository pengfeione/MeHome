package com.mehome.pay.iface;

public interface IPaymentInfo<PayChannel extends IPayChannel> {
	public String getPaymentId();
	//TODO 完成这个，与caller约定的签名
	public String getSign();
}
