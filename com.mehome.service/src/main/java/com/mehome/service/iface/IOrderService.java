package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.OrderBean;

public interface IOrderService {

	public List<OrderBean> getListByCondition(OrderBean bean);
	
	public String placeOrder(OrderBean bean);
	
	public String payOrder(OrderBean bean);
	
	public Long getSizeByCondition(OrderBean bean);
	
	public String updateOrder(OrderBean bean);
	
}
