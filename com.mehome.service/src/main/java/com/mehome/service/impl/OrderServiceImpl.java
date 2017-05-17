package com.mehome.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.mehome.dao.ProductRalationWelfareDao;
import com.mehome.dao.SupplierListDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.HouseResource;
import com.mehome.domain.OrderList;
import com.mehome.domain.ProductList;
import com.mehome.domain.ProductRalationWelfare;
import com.mehome.domain.SupplierList;
import com.mehome.domain.UserInfo;
import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.enumDTO.UserCompanyEnum;
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
	private ProductRalationWelfareDao productRalationWelfareDAO;

	@Override
	public List<OrderBean> getListByCondition(OrderBean bean) {
		List<OrderList> orderList = orderListDAO.getListByCondition(bean);
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
		if (orderList != null && orderList.size() > 0) {
			for (OrderList order : orderList) {
				OrderBean newBean = new OrderBean(order);
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
		// TODO Auto-generated method stub
		// String iface=
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
			if (bean.getOrderStatus() != null) {
				OrderList oldOrder = orderListDAO.selectById(bean.getOrderId());
				if (bean.getOrderStatus() <= oldOrder.getOrderStatus()) {
					log.error("订单状态不可前置到之前状态");
					return Boolean.FALSE.toString();
				}
				if (bean.getOrderStatus() == OrderStatusEnum.CONFIRMED.getKey()) {
					bean = calculateWelfare(bean);
				}
			}
			order = bean.beanToPojo(Boolean.FALSE);
			int row = orderListDAO.updateRequired(order);
		} catch (Exception e) {
			log.error("更新订单出错:" + e);
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}

	@Override
	public OrderBean calculateWelfare(OrderBean bean) {
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
					List<ProductRalationWelfare> ralationList = productRalationWelfareDAO
							.getRalationList(product.getProductId());
					for (ProductRalationWelfare productRalationWelfare : ralationList) {
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

}
