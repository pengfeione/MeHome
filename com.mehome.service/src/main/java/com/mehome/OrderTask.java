package com.mehome;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.DateUtils;
@Component
public class OrderTask {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
    private IOrderService orderService;
	 //每日凌晨 更新已经确认的订单  是否到达入住时间   以及 结束时间，进行对应状态的更新   @Scheduled(cron="0 0 1  * * ? ")
    @Scheduled(cron="0 0/5 * * * ?")
    public void updateOrderStatus(){
    	log.info("触发更新订单状态进程");
    	Date date = new Date();
    	Date compareDate= date;
    	OrderBean bean=new OrderBean();
    	bean.setOrderStatus(OrderStatusEnum.CONFIRMED.getKey());
    	List<OrderBean> list=orderService.getListByCondition(bean);
    	for (OrderBean orderBean : list) {
    		String startTime=orderBean.getStartTime();
    		String endTime=orderBean.getEndTime();
    		//更新为入住状态
    		Date start=DateUtils.strToDate(startTime);
    		Date end=DateUtils.strToDate(endTime);
    		if(compareDate.after(start)&&compareDate.before(end)){
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
    		if(end.before(compareDate)){
    			OrderBean updateBean=new OrderBean();
    			updateBean.setOrderId(orderBean.getOrderId());
    			updateBean.setOrderStatus(OrderStatusEnum.END.getKey());
    			orderService.updateOrder(updateBean);
    		}
		}
    }
}
