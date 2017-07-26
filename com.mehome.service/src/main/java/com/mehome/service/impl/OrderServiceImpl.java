package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mehome.dao.*;
import com.mehome.domain.*;
import com.mehome.enumDTO.HouseStatusEnum;
import com.mehome.enumDTO.OperationTypeEnum;
import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.enumDTO.PayStatusEnum;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.requestDTO.HouseBean;
import com.mehome.requestDTO.OrderBean;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.resonpseDTO.HouseTimePiece;
import com.mehome.service.iface.IOrderService;
import com.mehome.service.iface.IThirdPay;
import com.mehome.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	@Autowired
	private ThirdPartyPaymentDao thirdPartyPaymentDAO;

	@Override
	public List<OrderBean> getListByCondition(OrderBean bean) {
		List<OrderList> orderList = orderListDAO.getListByCondition(bean);
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();
		if (orderList != null && orderList.size() > 0) {
			for (OrderList order : orderList) {
				OrderBean newBean = new OrderBean(order);
				HouseResource resource = houseResourceDAO.selectById(newBean.getHouseId());
				HouseBean house = new HouseBean(resource);
				newBean.setHouse(house);
				orderBeanList.add(newBean);
			}
		}
		return orderBeanList;
	}

	@Override
	public synchronized String placeOrder(OrderBean bean) {
		System.out.println("a");
		OrderList order = null;
		try {
			Integer houseId = bean.getHouseId();
			HouseResource house = houseResourceDAO.selectById(houseId);
			if (house != null && house.getStatus().intValue() != HouseStatusEnum.AVAILABLE.getKey()) {
				log.error("房源[" + houseId + "]不处于可租赁状态");
				return "";
			}
			if (house != null && house.getProductId() != null) {
				ProductList product = productListDAO.selectById(house.getProductId());
				bean.setHouseSubject(house.getSubject());
				bean.setProductId(house.getProductId());
				bean.setProductName(product.getProductName());
				bean.setSupplierId(product.getSupplierId());
				SupplierList supplier = supplierListDAO.selectById(product.getSupplierId());
				bean.setSupplierName(supplier.getSupplierName());
				bean = calculateWelfare(bean, 1);
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
		String payType = bean.getPayType();
		// 动态加载对应支付渠道的实现类
		IThirdPay payImpl = SpringContextUtil.getBean(payType);
		ThirdPayMentBean paybean = new ThirdPayMentBean();
		JSONObject payRet = payImpl.pay(paybean);
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
			if ((bean.getOrderStatus() != null
					&& bean.getOrderStatus().intValue() == OrderStatusEnum.CONFIRMED.getKey())
					|| (oldOrder.getOrderStatus().intValue() == 1)) {
				bean = calculateWelfare(bean, null);
				// 更新房源为已经租借状态
				// Integer houseId = oldOrder.getHouseId();
				// HouseResource house = houseResourceDAO.selectById(houseId);
				// house.setStatus(HouseStatusEnum.LEASED.getKey());
				// house.setLeaseHolder(oldOrder.getBiller());
				// houseResourceDAO.update(house);
			}
			order = bean.compareToPojo();
			if (bean.getOrderStatus() != null && bean.getOrderStatus().intValue() == OrderStatusEnum.CANCEL.getKey()) {
				order.setStartTime(null);
				order.setEndTime(null);
			}
			if (order.getStartTime() != null && order.getEndTime() != null) {
				Long mills = order.getEndTime().getTime() - order.getStartTime().getTime();
				Long day = mills / (1000 * 60 * 60 * 24);
				order.setTenancy(new BigDecimal(day));
				// 根据租赁期重新计算房租 未满月按满月算
				Double dividedDouble = day / 31.00;
				Long dividedLong = day / 31;
				Long calculateMonth = dividedLong;
				if (dividedDouble > dividedLong) {
					calculateMonth = dividedLong + 1;
				}
				bean = calculateWelfare(bean, calculateMonth.intValue());
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
	public OrderBean calculateWelfare(OrderBean bean, Integer calculateMonth) {
		// TODO Auto-generated method stub
		String orderId = bean.getOrderId();
		OrderList order = null;
		if (StringUtils.isEmpty(orderId)) {
			order = bean.beanToPojo(Boolean.TRUE);
		} else {
			order = orderListDAO.selectById(orderId);
		}
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
					List<Integer> ralationList = productRelationWelfareDAO.selectByProductId(product.getProductId());
					for (Integer welfareId : ralationList) {
						CompanyWelfare welfare = companyWelfareDAO.selectById(welfareId);
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
					if (StringUtils.isNotEmpty(product.getPersonalWelfare())) {
						JSONObject personalWelfare = JSONObject.parseObject(product.getPersonalWelfare());
						mortagageNum = personalWelfare.getInteger("mortagageNum");
						payMentNum = personalWelfare.getInteger("payMentNum");
						remitPercent = personalWelfare.getInteger("remitPercent");
					} else {
						mortagageNum = 1;
						payMentNum = 1;
						remitPercent = 0;
					}
					// 先取个人福利里面的支付方式
					// &&!StringUtils.isEmpty(product.getPersonalWelfare())

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
			if (calculateMonth != null) {
				payMentNum = calculateMonth;
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
		if (StringUtils.isEmpty(orderId)) {
			log.error("订单号未传");
			return Boolean.FALSE.toString();
		}
		OrderList order = orderListDAO.selectById(orderId);
		if (order.getOrderStatus() == null || (order.getOrderStatus() != null
				&& order.getOrderStatus().intValue() != OrderStatusEnum.CANCEL.getKey())) {
			log.error("当前订单状态下不可进行退款操作");
			return Boolean.FALSE.toString();
		}
		if (order != null && order.getPlatformHost()) {
			String uid = order.getBiller();
			Integer deposit = order.getDeposit();
			UserInfo user = userInfoDAO.selectById(Integer.parseInt(uid));
			Integer oldAmount = user.getUserAmount();
			user.setUserAmount(oldAmount + deposit);
			userInfoDAO.updateRequired(user);
			UserAccountOperation oper = new UserAccountOperation();
			oper.setBalanceChange(deposit);
			oper.setOrderId(bean.getOrderId());
			oper.setOperationIndex((int) System.nanoTime());
			oper.setPlatform("plat");
			oper.setUserId(uid);
			oper.setOperationTime(new Date());
			oper.setOperationType(OperationTypeEnum.PLAT_REFUND.getKey());
			oper.setOperationDesc(OperationTypeEnum.PLAT_REFUND.getValue());
			oper.setOperationId(OrderIdUtils.getUUID());
			userAccountOperationDAO.insertRequired(oper);
			bean.setDepositBack(Boolean.TRUE);
			OrderList updateOrder = bean.compareToPojo();
			orderListDAO.updateRequired(updateOrder);
			return Boolean.TRUE.toString();
		}
		return null;
	}

	public static void main(String[] args) {
		Date date = new Date();
		Date a = DateUtils.getToDayStart(date);
		String dateStr = "2017-6-6 00:00:01";
		Date b = DateUtils.strToDate(dateStr);
		System.out.println(a.compareTo(b));

	}

	@Override
	public String judgeExistOrder(OrderBean bean) {
		Date now = new Date();
		Integer houseId = bean.getHouseId();
		if (houseId == null) {
			log.error("houseId未传");
			return "";
		}
		String biller = bean.getBiller();
		if (StringUtils.isBlank(biller)) {
			log.error("biller未传");
			return "";
		}
		HouseResource house = houseResourceDAO.selectById(houseId);
		OrderBean requestBean = new OrderBean();
		requestBean.setHouseId(houseId);
		requestBean.setBiller(biller);
		requestBean.setOrderStatus(OrderStatusEnum.ORDER.getKey());
		long num = orderListDAO.getSizeByCondition(requestBean);
		// TODO 根据时间片更新房源状态
		List<HouseTimePiece> pieceList = orderListDAO.houseTimePiece(houseId);
		for (HouseTimePiece houseTimePiece : pieceList) {
			String startTime = houseTimePiece.getStartTime();
			String endTime = houseTimePiece.getEndTime();
			Date startDate = DateUtils.strToDate(startTime);
			Date endDate = DateUtils.strToDate(endTime);
			if (now.after(startDate) && now.before(endDate)) {
				return Boolean.FALSE.toString();
			}
		}
		if (house != null && house.getStatus().intValue() == HouseStatusEnum.AVAILABLE.getKey() && num == 0) {
			return Boolean.TRUE.toString();
		}
		return Boolean.FALSE.toString();
	}

	@Override
	public List<HouseTimePiece> houseTimePiece(OrderBean bean) {
		AssertUtils.isNotNull(bean.getOrderId(), "订单编号不能为空！");
		OrderList orderList = orderListDAO.selectById(bean.getOrderId());
		List<HouseTimePiece> houseTimePieceList = new ArrayList<HouseTimePiece>();
		if (null != orderList) {
			HouseTimePiece houseTimePiece = new HouseTimePiece();
			if (null != orderList.getStartTime() && null != orderList.getEndTime()) {
				houseTimePiece.setStartTime(DateUtils.dateToDateStr(orderList.getStartTime()));
				houseTimePiece.setEndTime(DateUtils.dateToDateStr(orderList.getEndTime()));
			} else {
				houseTimePiece.setStartTime("");
				houseTimePiece.setEndTime("");
			}
			houseTimePieceList.add(houseTimePiece);
			if (null == orderList.getHouseId()) {
				return houseTimePieceList;
			} else {
				houseTimePieceList
						.addAll(orderListDAO.houseTimePieceExceptMe(orderList.getHouseId(), bean.getOrderId()));
			}
		}
		return houseTimePieceList;
	}

	@Override
	public List<HouseTimePiece> pieceByHouse(HouseResource bean) {
		AssertUtils.isNotNull(bean.getHouseId(), "订单编号不能为空！");
		return orderListDAO.houseTimePiece(bean.getHouseId());
	}

	@Override
	public void payNotify(ThirdPayMentBean thirdPay) {
		try {
			log.info("收到支付结果通知");
			Date date = new Date();
			// TODO 处理通知对象 更新 orderlist表记录 和 thirdpay表记录
			// TODO 查询根据第三方交易流水 是否已经存在，存在不进行更新
			String tradeSeq = thirdPay.getTradeSeq();
			ThirdPayMentBean requestBean = new ThirdPayMentBean();
			requestBean.setTradeSeq(tradeSeq);
			long num = thirdPartyPaymentDAO.countByCondition(requestBean);
			if (num > 0) {
				log.error("第三方流水中已经存在流水[" + tradeSeq + "],不予后续处理");
				return;
			}
			String orderId = thirdPay.getOrderId();
			OrderList order = orderListDAO.selectById(orderId);
			ThirdPayMentBean saveBean = new ThirdPayMentBean(order);
			ThirdPartyPayment thirdObj = saveBean.beanToPojo();
			thirdObj.setCallbackUrl("http://api.mjiahome.com//wx/order/notify");
			thirdObj.setPayAccount(thirdPay.getOpenId());
			thirdObj.setReceiveAccount(thirdPay.getReceiveAccount());
			thirdObj.setPaymentId(OrderIdUtils.getUUID());
			thirdObj.setPayStatus(PayStatusEnum.PAY_SUCCESS.getKey());
			thirdPartyPaymentDAO.insert(thirdObj);
			order.setPayTime(date);
			order.setPayFlow(thirdPay.getTradeSeq());
			order.setUpdateTime(date);
			order.setPayAccount(thirdPay.getOpenId());
			orderListDAO.update(order);
		} catch (Exception e) {
			log.error("支付通知结果处理出错:" + e);
		}

	}

	@Override
	public JSONObject paymentCreateOrder(ThirdPayMentBean bean) {
		// TODO 创建第三方订单
		String payType = bean.getPayType();
		String orderId = bean.getOrderId();
		String payer = bean.getPayer();
		String openId = null;
		if (StringUtils.isNotBlank(payer)) {
			UserInfo info = userInfoDAO.selectById(Integer.parseInt(payer));
			if (info != null && StringUtils.isNotBlank(info.getOpenId())) {
				openId = info.getOpenId();
			} else {
				log.error("未获取到用户的openId");
				return null;
			}
		} else {
			log.error("未传payer属性");
			return null;
		}
		// 动态加载对应支付渠道的实现类
		IThirdPay payImpl = SpringContextUtil.getBean(payType);
		// TODO 将订单有关数据塞进第三方支付对象中
		OrderList order = orderListDAO.selectById(orderId);
		if (order.getPlatformHost()) {
			order.setPayAmount(order.getDeposit());
		}
		order.setPayer(payer);
		orderListDAO.update(order);
		ThirdPayMentBean paybean = new ThirdPayMentBean(order);
		paybean.setTradeType(bean.getTradeType());

		paybean.setOpenId(openId);
		return payImpl.pay(paybean);
	}
}
