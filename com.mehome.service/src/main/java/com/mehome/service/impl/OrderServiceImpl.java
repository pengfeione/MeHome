package com.mehome.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mehome.dao.CompanyWelfareDao;
import com.mehome.dao.HouseResourceDao;
import com.mehome.dao.OrderListDao;
import com.mehome.dao.ProductListDao;
import com.mehome.dao.ProductRelationWelfareDao;
import com.mehome.dao.SupplierListDao;
import com.mehome.dao.UserAccountOperationDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.HouseResource;
import com.mehome.domain.OrderList;
import com.mehome.domain.ProductList;
import com.mehome.domain.ProductRelationWelfare;
import com.mehome.domain.SupplierList;
import com.mehome.domain.UserAccountOperation;
import com.mehome.domain.UserInfo;
import com.mehome.enumDTO.OperationTypeEnum;
import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.requestDTO.HouseBean;
import com.mehome.requestDTO.OrderBean;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.service.iface.IThirdPay;
import com.mehome.utils.DateUtils;
import com.mehome.utils.OrderIdUtils;
import com.mehome.utils.PropertiesUtil;
import com.mehome.utils.SpringContextUtil;

@Service("IOrderService")
public class OrderServiceImpl implements IOrderService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private OrderListDao orderListDAO;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private HouseResourceDao houseResourceDAO;
	@Autowired
	private ProductListDao productListDAO;
	@Autowired
	private SupplierListDao supplierListDAO;
	@Autowired
	private UserInfoDao userInfoDAO;
	@Autowired
	private CompanyWelfareDao companyWelfareDAO;
	@Autowired
	private ProductRelationWelfareDao productRelationWelfareDAO;
	@Autowired
	private UserAccountOperationDao userAccountOperationDAO;

	@Override
	public List<OrderBean> getListByCondition(OrderBean bean) {
		List<OrderList> orderList = orderListDAO.getListByCondition(bean);
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
		if (orderList != null && orderList.size() > 0) {
			for (OrderList order : orderList) {
				OrderBean newBean = new OrderBean(order);
				HouseResource resource=houseResourceDAO.selectById(newBean.getHouseId());
				HouseBean house=new HouseBean(resource);
				newBean.setHouse(house);
				orderBeanList.add(newBean);
			}
		}
		return orderBeanList;
	}

	@Override
	public synchronized String placeOrder(OrderBean bean) {
		OrderList order = null;
		try {
			Integer houseId = bean.getHouseId();
			HouseResource house = houseResourceDAO.selectById(houseId);
			if (house != null && house.getProductId() != null) {
				ProductList product = productListDAO.selectById(house.getProductId());
				bean.setHouseSubject(house.getSubject());
				bean.setProductId(house.getProductId());
				bean.setProductName(product.getProductName());
			}
			order = bean.beanToPojo(Boolean.TRUE);
			orderListDAO.insert(order);
		} catch (Exception e) {
			log.error("生成订单出错:" + e);
		}
		// TODO Auto-generated method stub
		return StringUtils.isEmpty(order.getOrderId()) ? "" : order.getOrderId().toString();
	}

	@Override
	public synchronized String payOrder(OrderBean bean) {
		String payType=bean.getPayType();
		//动态加载对应支付渠道的实现类
		IThirdPay payImpl=SpringContextUtil.getBean(payType);
		ThirdPayMentBean paybean=new ThirdPayMentBean();
		ThirdPayMentBean payRet=payImpl.pay(paybean);
		return null;
	}

	@Override
	public Long getSizeByCondition(OrderBean bean) {
		Long size = orderListDAO.getSizeByCondition(bean);
		return size;
	}

	@Override
	public synchronized String updateOrder(OrderBean bean) {
		OrderList order = null;
		try {
			OrderList oldOrder = orderListDAO.selectById(bean.getOrderId());
			if (bean.getOrderStatus() != null) {
				if (bean.getOrderStatus().intValue() <= oldOrder.getOrderStatus()) {
					log.error("订单状态不可前置到之前状态");
					return Boolean.FALSE.toString();
				}
			}
			if ((bean.getOrderStatus()!=null&&bean.getOrderStatus().intValue() == OrderStatusEnum.CONFIRMED.getKey())||(oldOrder.getOrderStatus().intValue()==1)) {
				bean = calculateWelfare(bean,null);
				//TODO 更新房源为已经租借状态
			}
			order = bean.compareToPojo();
			if(order.getStartTime()!=null&&order.getEndTime()!=null){
				Long mills=order.getEndTime().getTime()-order.getStartTime().getTime();
				Long day=mills/(1000*60*60*24);
				order.setTenancy(new BigDecimal(day));
				//根据租赁期重新计算房租 未满月按满月算
				Double dividedDouble=day/31.00;
				Long dividedLong=day/31;
				Long calculateMonth=dividedLong;
				if(dividedDouble>dividedLong){
					calculateMonth=dividedLong+1;
				}
				bean = calculateWelfare(bean,calculateMonth.intValue());
				order.setOrigRent(bean.getOrigRent());
				order.setOrigAmount(bean.getOrigAmount());
				order.setDiscountRent(bean.getDiscountRent());
				order.setDiscountAmount(bean.getDiscountAmount());
			}
			int row = orderListDAO.updateRequired(order);
		} catch (Exception e) {
			log.error("更新订单出错:" + e);
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}

	@Override
	public OrderBean calculateWelfare(OrderBean bean,Integer calculateMonth) {
		// TODO Auto-generated method stub
		String orderId = bean.getOrderId();
		OrderList order = orderListDAO.selectById(orderId);
		String biller = order.getBiller();
		Integer userCompanyId = null;
		if (!StringUtils.isEmpty(biller)) {
			UserInfo user = userInfoDAO.selectById(Integer.parseInt(biller));
			if (user != null && user.getCompanyStatus() == UserCompanyEnum.ACTIVE.getKey()) {
				userCompanyId = user.getCompanyId();
			}
		}
		Integer houseId = order.getHouseId();
		HouseResource house = houseResourceDAO.selectById(houseId);
		Integer mortagageNum = 1;
		Integer payMentNum = 3;
		Integer remitPercent = 0;
		if (house != null && house.getProductId() != null) {
			ProductList product = productListDAO.selectById(house.getProductId());
			if (product != null) {
				if (product.getIsWelfare() && userCompanyId != null) {
					// 有企业福利
					List<ProductRelationWelfare> ralationList = productRelationWelfareDAO
							.selectByProductId(product.getProductId());
					for (ProductRelationWelfare productRalationWelfare : ralationList) {
						CompanyWelfare welfare = companyWelfareDAO.selectById(productRalationWelfare.getWelfareId());
						if (welfare != null && welfare.getWelfareActive() && welfare.getCompanyId() == userCompanyId) {
							JSONObject companyWelfare = JSONObject.parseObject(welfare.getWelfareContent());
							if (companyWelfare != null) {
								mortagageNum = companyWelfare.getInteger("mortagageNum");
								payMentNum = companyWelfare.getInteger("payMentNum");
								remitPercent = companyWelfare.getInteger("remitPercent");
							}
						}
					}

				} else {
					// 先取个人福利里面的支付方式
					// &&!StringUtils.isEmpty(product.getPersonalWelfare())
					JSONObject personalWelfare = JSONObject.parseObject(product.getPersonalWelfare());
					mortagageNum = personalWelfare.getInteger("mortagageNum");
					payMentNum = personalWelfare.getInteger("payMentNum");
					remitPercent = personalWelfare.getInteger("remitPercent");
				}
			} else {
				String payType = house.getPayType();
				JSONObject payTypeJson = JSONObject.parseObject(payType);
				mortagageNum = (payTypeJson == null ? 1 : payTypeJson.getInteger("mortagageNum"));
				payMentNum = (payTypeJson == null ? 1 : payTypeJson.getInteger("payMentNum"));
			}
			SupplierList supplier = supplierListDAO.selectById(house.getSupplierId());
			Double percent = 1.00;
			if (supplier != null) {
				BigDecimal depositPercent = supplier.getDepositPercent();
				Double supplierPercent = depositPercent.doubleValue() * 0.01;
				percent = percent - supplierPercent;
			}
			Integer deposit = (int) (house.getRoomRent() * mortagageNum * percent);
			if(calculateMonth!=null){
				payMentNum=calculateMonth;
			}
			Double discountRent = house.getRoomRent() * payMentNum * (1 - remitPercent * 0.01);
			bean.setDeposit(deposit);
			bean.setHouseSubject(house.getSubject());
			bean.setProductId(house.getProductId());
			bean.setProductName(product.getProductName());
			bean.setOrigRent(house.getRoomRent() * payMentNum);
			bean.setOrigAmount(deposit + bean.getOrigRent());
			bean.setDiscountRent(discountRent.intValue());
			bean.setDiscountAmount(deposit + bean.getDiscountRent());
		}
		return bean;
	}

	@Override
	public String refundOrder(OrderBean bean) {
		String orderId = bean.getOrderId();
		if (StringUtils.isEmpty(orderId)){
			log.error("订单号未传");
			return Boolean.FALSE.toString();
		}
		OrderList order = orderListDAO.selectById(orderId);
		if (order.getOrderStatus() == null||(order.getOrderStatus()!=null&&order.getOrderStatus().intValue()!=OrderStatusEnum.CANCEL.getKey())) {
			log.error("当前订单状态下不可进行退款操作");
			return Boolean.FALSE.toString();
		}
		if(order!=null&&order.getPlatformHost()){
			String uid=order.getBiller();
			Integer deposit=order.getDeposit();
			UserInfo user = userInfoDAO.selectById(Integer.parseInt(uid));
			Integer oldAmount=user.getUserAmount();
			user.setUserAmount(oldAmount+deposit);
			userInfoDAO.updateRequired(user);
			UserAccountOperation oper=new UserAccountOperation();
			oper.setBalanceChange(deposit);
			oper.setOrderId(bean.getOrderId());
			oper.setOperationIndex((int)System.nanoTime());
			oper.setPlatform("plat");
			oper.setUserId(uid);
			oper.setOperationTime(new Date());
			oper.setOperationType(OperationTypeEnum.PLAT_REFUND.getKey());
			oper.setOperationDesc(OperationTypeEnum.PLAT_REFUND.getValue());
			oper.setOperationId(OrderIdUtils.getUUID());
			userAccountOperationDAO.insertRequired(oper);
			bean.setDepositBack(Boolean.TRUE);
			OrderList updateOrder=bean.beanToPojo(Boolean.FALSE);
			orderListDAO.updateRequired(updateOrder);
			return Boolean.TRUE.toString();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		Date a=DateUtils.getToDayStart(date);
		String dateStr="2017-6-6 00:00:01";
		Date b=DateUtils.strToDate(dateStr);
		System.out.println(a.compareTo(b));
		
	}

}
