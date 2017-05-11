package com.mehome.requestDTO;

import com.mehome.domain.OrderList;
import com.mehome.utils.DateUtils;
import com.mehome.utils.PageMysqlUtil;

import java.math.BigDecimal;
import java.util.Date;

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
	
	public OrderList beanToPojo(){
		return null;
		
	}
	
	

}
