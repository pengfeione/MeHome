package com.mehome.pay.iface;

import javax.servlet.http.HttpServletRequest;

import com.mehome.exceptions.BaseException;

public interface IPaymentService<PaidInfo extends BasePaidInfo<NotifyInfo, PayChannel>, NotifyInfo extends INotifyInfo<PayChannel>, PaymentInfo extends IPaymentInfo<PayChannel>, PayChannel extends IPayChannel> {
	public <TypedPaymentInfo extends PaymentInfo> TypedPaymentInfo createPaymentInfo(HttpServletRequest request)
			throws BaseException;

	public NotifyInfo parseNotifyInfo(HttpServletRequest request) throws BaseException;

	public void processNotifyInfo(NotifyInfo notifyInfo) throws BaseException;
}
