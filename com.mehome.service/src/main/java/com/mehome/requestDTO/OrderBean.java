package com.mehome.requestDTO;

import com.mehome.domain.OrderList;
import com.mehome.enumDTO.OrderStatusEnum;
import com.mehome.utils.DateUtils;
import com.mehome.utils.OrderIdUtils;
import com.mehome.utils.PageMysqlUtil;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 订单
 * @author xuyixun21
 *
 */
public class OrderBean extends PageMysqlUtil{
	
	private String biller;
	
	private Date billTimeBegin;
	
	private Date billTimeEnd;
	
	private Integer orderStatus;
	
	private Date payTimeBegin;
	
	private Date payTimeEnd;
	
	private Integer productId;
	
	private String productName;
	
	private Integer houseId;
	
	private String houseSubject;
	
	private Integer supplierId;
	
	private String supplierName;
	
	private String payer;
	
	private String billTime;
	
	private String payTime;
	
	private String billerPhone;
	
	private String address;
	/**
	 * 押金
	 */
	private Integer deposit;
	
	private Integer origRent;
	
	private Integer origAmount;
	
	private Integer discountRent;
	
	private Integer discountAmount;
	
	private String payAccount;
	
	private Boolean payOnline;
	
	private String payType;
	
	private String payFlow;
	
	private Integer payAmount;
	
	private BigDecimal tenancy;
	
	private Boolean depositBack;
	
	private Integer backAmount;
	
	private String startTime;
	
	private String endTime;
	
	private Boolean platformHost;
	
	private String updateTime;
	
	private String orderReason;
	
	private String orderId;
	
	private HouseBean house;
	
	private String orderStatusDesc;

	public String getBiller() {
		return biller;
	}

	public void setBiller(String biller) {
		this.biller = biller;
	}

	public Date getBillTimeBegin() {
		return billTimeBegin;
	}

	public void setBillTimeBegin(Date billTimeBegin) {
		this.billTimeBegin = billTimeBegin;
	}

	public Date getBillTimeEnd() {
		return billTimeEnd;
	}

	public void setBillTimeEnd(Date billTimeEnd) {
		this.billTimeEnd = billTimeEnd;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getPayTimeBegin() {
		return payTimeBegin;
	}

	public void setPayTimeBegin(Date payTimeBegin) {
		this.payTimeBegin = payTimeBegin;
	}

	public Date getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(Date payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getHouseSubject() {
		return houseSubject;
	}

	public void setHouseSubject(String houseSubject) {
		this.houseSubject = houseSubject;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getBillTime() {
		return billTime;
	}

	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getBillerPhone() {
		return billerPhone;
	}

	public void setBillerPhone(String billerPhone) {
		this.billerPhone = billerPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getOrigRent() {
		return origRent;
	}

	public void setOrigRent(Integer origRent) {
		this.origRent = origRent;
	}

	public Integer getOrigAmount() {
		return origAmount;
	}

	public void setOrigAmount(Integer origAmount) {
		this.origAmount = origAmount;
	}

	public Integer getDiscountRent() {
		return discountRent;
	}

	public void setDiscountRent(Integer discountRent) {
		this.discountRent = discountRent;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public Boolean getPayOnline() {
		return payOnline;
	}

	public void setPayOnline(Boolean payOnline) {
		this.payOnline = payOnline;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayFlow() {
		return payFlow;
	}

	public void setPayFlow(String payFlow) {
		this.payFlow = payFlow;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getTenancy() {
		return tenancy;
	}

	public void setTenancy(BigDecimal tenancy) {
		this.tenancy = tenancy;
	}

	public Boolean getDepositBack() {
		return depositBack;
	}

	public void setDepositBack(Boolean depositBack) {
		this.depositBack = depositBack;
	}

	public Integer getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(Integer backAmount) {
		this.backAmount = backAmount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getPlatformHost() {
		return platformHost;
	}

	public void setPlatformHost(Boolean platformHost) {
		this.platformHost = platformHost;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public HouseBean getHouse() {
		return house;
	}

	public void setHouse(HouseBean house) {
		this.house = house;
	}

	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}

	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	public OrderBean(){
		
	}
	public OrderBean(OrderList order){
		this.setAddress(order.getAddress());
		this.setBackAmount(order.getBackAmount());
		this.setBiller(order.getBiller());
		this.setBillerPhone(order.getBillerPhone());
		this.setBillTime(order.getBillTime()==null?null:DateUtils.dateToStr(order.getBillTime()));
		this.setDeposit(order.getDeposit());
		this.setDepositBack(order.getDepositBack());
		this.setDiscountAmount(order.getDiscountAmount());
		this.setDiscountRent(order.getDiscountRent());
		this.setEndTime(order.getEndTime()==null?null:DateUtils.dateToStr(order.getEndTime()));
		this.setHouseId(order.getHouseId());
		this.setHouseSubject(order.getHouseSubject());
		this.setOrderReason(order.getOrderReason());
		this.setOrderStatus(order.getOrderStatus());
		OrderStatusEnum[] enums=OrderStatusEnum.values();
		for (OrderStatusEnum orderStatusEnum : enums) {
			if(order.getOrderStatus()==orderStatusEnum.getKey()){
				this.setOrderStatusDesc(orderStatusEnum.getValue());
			}
		}
		this.setOrderStatusDesc("");
		this.setOrigAmount(order.getOrigAmount());
		this.setOrigRent(order.getOrigRent());
		this.setPayAccount(order.getPayAccount());
		this.setPayAmount(order.getPayAmount());
		this.setPayer(order.getPayer());
		this.setPayFlow(order.getPayFlow());
		this.setPayOnline(order.getPayOnline());
		this.setPayTime(order.getPayTime()==null?null:DateUtils.dateToStr(order.getPayTime()));
		this.setPayType(order.getPayType());
		this.setPlatformHost(order.getPlatformHost());
		this.setProductId(order.getProductId());
		this.setProductName(order.getProductName());
		this.setStartTime(order.getStartTime()==null?null:DateUtils.dateToStr(order.getStartTime()));
		this.setSupplierId(order.getSupplierId());
		this.setSupplierName(order.getSupplierName());
		this.setTenancy(order.getTenancy());
		this.setUpdateTime(order.getUpdateTime()==null?null:DateUtils.dateToStr(order.getUpdateTime()));
	}
	
	public OrderList beanToPojo(Boolean addBoolean){
		OrderList order=new OrderList();
		Date date=new Date();
		order.setAddress(StringUtils.isEmpty(this.getAddress())&&addBoolean?"未填写联系地址":this.getAddress());
		order.setBackAmount(this.getBackAmount()==null&&addBoolean?0:this.getBackAmount());
		order.setBiller(StringUtils.isEmpty(this.getBiller())&&addBoolean?"0":this.getBiller());
		order.setBillerPhone(StringUtils.isEmpty(this.getBillerPhone())&&addBoolean?"0":this.getBillerPhone());
		order.setBillTime(StringUtils.isEmpty(this.getBillTime())&&addBoolean?date:(StringUtils.isEmpty(this.getBillTime())?null:DateUtils.strToDate(this.getBillTime())));
		order.setDeposit(this.getDeposit()==null&&addBoolean?0:this.getDeposit());
		order.setDepositBack(this.getDepositBack()==null&&addBoolean?Boolean.FALSE:this.getDepositBack());
		order.setDiscountAmount(this.getDiscountAmount()==null&&addBoolean?0:this.getDiscountAmount());
		order.setDiscountRent(this.getDiscountRent()==null&&addBoolean?0:this.getDiscountRent());
		order.setEndTime(StringUtils.isEmpty(this.getEndTime())&&addBoolean?date:(StringUtils.isEmpty(this.getEndTime())?null:DateUtils.strToDate(this.getEndTime())));
		order.setHouseId(this.getHouseId()==null&&addBoolean?0:this.getHouseId());
		order.setHouseSubject(StringUtils.isEmpty(this.getHouseSubject())&&addBoolean?"房源标题":this.getHouseSubject());
		order.setOrderId(StringUtils.isEmpty(this.getOrderId())&&addBoolean?OrderIdUtils.getOrderId(order.getBiller()):this.getOrderId());
		order.setOrderReason(StringUtils.isEmpty(this.getOrderReason())&&addBoolean?"正常交易":this.getOrderReason());
		order.setOrderStatus(this.getOrderStatus()==null&&addBoolean?OrderStatusEnum.ORDER.getKey():this.getOrderStatus());
		order.setOrigAmount(this.getOrigAmount()==null&&addBoolean?0:this.getOrigAmount());
		order.setOrigRent(this.getOrigRent()==null&&addBoolean?0:this.getOrigRent());
		order.setPayAccount(this.getPayAccount());
		order.setPayAmount(this.getPayAmount());
		order.setPayer(this.getPayer());
		order.setPayFlow(this.getPayFlow());
		order.setPayOnline(this.getPayOnline());
		order.setPayTime(StringUtils.isEmpty(this.getPayTime())?null:DateUtils.strToDate(this.getPayTime()));
		order.setPayType(this.getPayType());
		order.setPlatformHost(this.getPlatformHost()==null?Boolean.FALSE:this.getPlatformHost());
		order.setProductId(this.getProductId());
		order.setProductName(this.getProductName());
		order.setStartTime(StringUtils.isEmpty(this.getStartTime())&&addBoolean?date:(StringUtils.isEmpty(this.getStartTime())?null:DateUtils.strToDate(this.getStartTime())));
		order.setSupplierId(this.getSupplierId());
		order.setSupplierName(this.getSupplierName());
		order.setTenancy(this.getTenancy()==null?new BigDecimal("0"):this.getTenancy());
		order.setUpdateTime(date);
		return order;
		
	}
	
	public static void main(String[] args) {
		OrderStatusEnum[] enums=OrderStatusEnum.values();
		for (OrderStatusEnum orderStatusEnum : enums) {
			System.out.println(orderStatusEnum.getKey()+"|"+orderStatusEnum.getValue());
		}
	}

}
