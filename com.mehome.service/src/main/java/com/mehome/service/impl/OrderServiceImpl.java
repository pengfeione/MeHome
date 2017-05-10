package com.mehome.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
@Service("IOrderService")
public class OrderServiceImpl implements IOrderService {

	@Override
	public List<OrderBean> getListByCondition(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String placeOrder(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String payOrder(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSizeByCondition(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String updateOrder(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
