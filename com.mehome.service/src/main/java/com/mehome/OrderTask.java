package com.mehome;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.DateUtils;
@Component
public class OrderTask {
	@Autowired
    private IOrderService orderService;
	 //每日凌晨 更新已经确认的订单  是否到达入住时间   以及 结束时间，进行对应状态的更新
    @Scheduled(cron="0 0 0  * * ? ")
    public void updateOrderStatus(){
    	Date date = new Date();
    	Date compareDate= DateUtils.getToDayStart(date);
    	OrderBean bean=new OrderBean();
    	bean.setOrderStatus(OrderStatusEnum.CONFIRMED.getKey());
    	List<OrderBean> list=orderService.getListByCondition(bean);
    	for (OrderBean orderBean : list) {
    		String startTime=orderBean.getStartTime();
    		//更新为入住状态
    		Date start=DateUtils.strToDate(startTime);
    		if(start.compareTo(compareDate)==0){
    			OrderBean updateBean=new OrderBean();
    			updateBean.setOrderId(orderBean.getOrderId());
    			updateBean.setOrderStatus(OrderStatusEnum.CHECKIN.getKey());
    			orderService.updateOrder(updateBean);
    		}
    	}
    	OrderBean checkBean=new OrderBean();
    	checkBean.setOrderStatus(OrderStatusEnum.CHECKIN.getKey());
    	List<OrderBean> checkList=orderService.getListByCondition(checkBean);
    	for (OrderBean orderBean : checkList) {
    		String endTime=orderBean.getEndTime();
    		//更新为结束状态
    		Date end=DateUtils.strToDate(endTime);
    		if(end.compareTo(compareDate)==0){
    			OrderBean updateBean=new OrderBean();
    			updateBean.setOrderId(orderBean.getOrderId());
    			updateBean.setOrderStatus(OrderStatusEnum.END.getKey());
    			orderService.updateOrder(updateBean);
    		}
		}
    }
}
