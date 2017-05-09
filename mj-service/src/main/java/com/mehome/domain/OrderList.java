package com.mehome.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrderList {
    private String orderId;

    private Integer productId;

    private String productName;

    private Integer houseId;

    private String houseSubject;

    private Integer supplierId;

    private String supplierName;

    private String biller;

    private String payer;

    private Date billTime;

    private Date payTime;

    private String billerPhone;

    private String address;

    private Integer deposit;

    private Integer origRent;

    private Integer origAmount;

    private Integer discountRent;

    private Integer discountAmount;

    private String payAccount;

    private Boolean payOnline;

    private String payType;

    private Integer payAmount;

    private BigDecimal tenancy;

    private Boolean depositBack;

    private Integer backAmount;

    private Date startTime;

    private Date endTime;

    private Boolean platformHost;

    private Date updateTime;

    private Integer orderStatus;

    private String orderReason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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
        this.productName = productName == null ? null : productName.trim();
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
        this.houseSubject = houseSubject == null ? null : houseSubject.trim();
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
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller == null ? null : biller.trim();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getBillerPhone() {
        return billerPhone;
    }

    public void setBillerPhone(String billerPhone) {
        this.billerPhone = billerPhone == null ? null : billerPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.payAccount = payAccount == null ? null : payAccount.trim();
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
        this.payType = payType == null ? null : payType.trim();
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getPlatformHost() {
        return platformHost;
    }

    public void setPlatformHost(Boolean platformHost) {
        this.platformHost = platformHost;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason == null ? null : orderReason.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderList other = (OrderList) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getHouseId() == null ? other.getHouseId() == null : this.getHouseId().equals(other.getHouseId()))
            && (this.getHouseSubject() == null ? other.getHouseSubject() == null : this.getHouseSubject().equals(other.getHouseSubject()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getBiller() == null ? other.getBiller() == null : this.getBiller().equals(other.getBiller()))
            && (this.getPayer() == null ? other.getPayer() == null : this.getPayer().equals(other.getPayer()))
            && (this.getBillTime() == null ? other.getBillTime() == null : this.getBillTime().equals(other.getBillTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getBillerPhone() == null ? other.getBillerPhone() == null : this.getBillerPhone().equals(other.getBillerPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDeposit() == null ? other.getDeposit() == null : this.getDeposit().equals(other.getDeposit()))
            && (this.getOrigRent() == null ? other.getOrigRent() == null : this.getOrigRent().equals(other.getOrigRent()))
            && (this.getOrigAmount() == null ? other.getOrigAmount() == null : this.getOrigAmount().equals(other.getOrigAmount()))
            && (this.getDiscountRent() == null ? other.getDiscountRent() == null : this.getDiscountRent().equals(other.getDiscountRent()))
            && (this.getDiscountAmount() == null ? other.getDiscountAmount() == null : this.getDiscountAmount().equals(other.getDiscountAmount()))
            && (this.getPayAccount() == null ? other.getPayAccount() == null : this.getPayAccount().equals(other.getPayAccount()))
            && (this.getPayOnline() == null ? other.getPayOnline() == null : this.getPayOnline().equals(other.getPayOnline()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getTenancy() == null ? other.getTenancy() == null : this.getTenancy().equals(other.getTenancy()))
            && (this.getDepositBack() == null ? other.getDepositBack() == null : this.getDepositBack().equals(other.getDepositBack()))
            && (this.getBackAmount() == null ? other.getBackAmount() == null : this.getBackAmount().equals(other.getBackAmount()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getPlatformHost() == null ? other.getPlatformHost() == null : this.getPlatformHost().equals(other.getPlatformHost()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getOrderReason() == null ? other.getOrderReason() == null : this.getOrderReason().equals(other.getOrderReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getHouseId() == null) ? 0 : getHouseId().hashCode());
        result = prime * result + ((getHouseSubject() == null) ? 0 : getHouseSubject().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getBiller() == null) ? 0 : getBiller().hashCode());
        result = prime * result + ((getPayer() == null) ? 0 : getPayer().hashCode());
        result = prime * result + ((getBillTime() == null) ? 0 : getBillTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getBillerPhone() == null) ? 0 : getBillerPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDeposit() == null) ? 0 : getDeposit().hashCode());
        result = prime * result + ((getOrigRent() == null) ? 0 : getOrigRent().hashCode());
        result = prime * result + ((getOrigAmount() == null) ? 0 : getOrigAmount().hashCode());
        result = prime * result + ((getDiscountRent() == null) ? 0 : getDiscountRent().hashCode());
        result = prime * result + ((getDiscountAmount() == null) ? 0 : getDiscountAmount().hashCode());
        result = prime * result + ((getPayAccount() == null) ? 0 : getPayAccount().hashCode());
        result = prime * result + ((getPayOnline() == null) ? 0 : getPayOnline().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getTenancy() == null) ? 0 : getTenancy().hashCode());
        result = prime * result + ((getDepositBack() == null) ? 0 : getDepositBack().hashCode());
        result = prime * result + ((getBackAmount() == null) ? 0 : getBackAmount().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getPlatformHost() == null) ? 0 : getPlatformHost().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getOrderReason() == null) ? 0 : getOrderReason().hashCode());
        return result;
    }
}