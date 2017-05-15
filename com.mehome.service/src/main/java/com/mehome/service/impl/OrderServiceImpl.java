package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.OrderListDao;
import com.mehome.domain.OrderList;
import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.PropertiesUtil;
@Service("IOrderService")
public class OrderServiceImpl implements IOrderService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private OrderListDao orderListDAO;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Override
	public List<OrderBean> getListByCondition(OrderBean bean) {
		List<OrderList> orderList=orderListDAO.getListByCondition(bean);
		List<OrderBean> orderBeanList=new ArrayList<OrderBean>();
		if(orderList!=null&&orderList.size()>0){
			for (OrderList order : orderList) {
				OrderBean newBean=new OrderBean(order);
				orderBeanList.add(newBean);
			}
		}
		return orderBeanList;
	}

	@Override
	public synchronized String placeOrder(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String payOrder(OrderBean bean) {
		// TODO Auto-generated method stub
//		String iface=
		return null;
	}

	@Override
	public Long getSizeByCondition(OrderBean bean) {
		Long size=orderListDAO.getSizeByCondition(bean);
		return size;
	}

	@Override
	public synchronized String updateOrder(OrderBean bean) {
		OrderList order = null;
		try {
			order = bean.beanToPojo();
			int row = orderListDAO.updateRequired(order);
		} catch (Exception e) {
			log.error("更新订单出错:" + e);
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}

}
