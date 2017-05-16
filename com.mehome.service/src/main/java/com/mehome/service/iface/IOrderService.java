package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.OrderBean;

public interface IOrderService {

	public List<OrderBean> getListByCondition(OrderBean bean);
	
	public String placeOrder(OrderBean bean);
	
	public String payOrder(OrderBean bean);
	
	public Long getSizeByCondition(OrderBean bean);
	
	public String updateOrder(OrderBean bean);
	/**
	 * 计算福利以及房租、押金
	 * @param bean
	 * @return
	 */
	public OrderBean calculateWelfare(OrderBean bean);
	
}
