package com.mehome.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierList {
    private Integer supplierId;

    private String supplierName;

    private String supplierPhone;

    private String recipientType;

    private String recipientAccount;

    private Boolean rentOnline;

    private BigDecimal rentPercent;

    private Boolean depositOnline;

    private BigDecimal depositPercent;

    private String account;

    private String password;

    private Date createTime;

    private Date updateTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone == null ? null : supplierPhone.trim();
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType == null ? null : recipientType.trim();
    }

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount == null ? null : recipientAccount.trim();
    }

    public Boolean getRentOnline() {
        return rentOnline;
    }

    public void setRentOnline(Boolean rentOnline) {
        this.rentOnline = rentOnline;
    }

    public BigDecimal getRentPercent() {
        return rentPercent;
    }

    public void setRentPercent(BigDecimal rentPercent) {
        this.rentPercent = rentPercent;
    }

    public Boolean getDepositOnline() {
        return depositOnline;
    }

    public void setDepositOnline(Boolean depositOnline) {
        this.depositOnline = depositOnline;
    }

    public BigDecimal getDepositPercent() {
        return depositPercent;
    }

    public void setDepositPercent(BigDecimal depositPercent) {
        this.depositPercent = depositPercent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        SupplierList other = (SupplierList) that;
        return (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
                && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
                && (this.getSupplierPhone() == null ? other.getSupplierPhone() == null : this.getSupplierPhone().equals(other.getSupplierPhone()))
                && (this.getRecipientType() == null ? other.getRecipientType() == null : this.getRecipientType().equals(other.getRecipientType()))
                && (this.getRecipientAccount() == null ? other.getRecipientAccount() == null : this.getRecipientAccount().equals(other.getRecipientAccount()))
                && (this.getRentOnline() == null ? other.getRentOnline() == null : this.getRentOnline().equals(other.getRentOnline()))
                && (this.getRentPercent() == null ? other.getRentPercent() == null : this.getRentPercent().equals(other.getRentPercent()))
                && (this.getDepositOnline() == null ? other.getDepositOnline() == null : this.getDepositOnline().equals(other.getDepositOnline()))
                && (this.getDepositPercent() == null ? other.getDepositPercent() == null : this.getDepositPercent().equals(other.getDepositPercent()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getSupplierPhone() == null) ? 0 : getSupplierPhone().hashCode());
        result = prime * result + ((getRecipientType() == null) ? 0 : getRecipientType().hashCode());
        result = prime * result + ((getRecipientAccount() == null) ? 0 : getRecipientAccount().hashCode());
        result = prime * result + ((getRentOnline() == null) ? 0 : getRentOnline().hashCode());
        result = prime * result + ((getRentPercent() == null) ? 0 : getRentPercent().hashCode());
        result = prime * result + ((getDepositOnline() == null) ? 0 : getDepositOnline().hashCode());
        result = prime * result + ((getDepositPercent() == null) ? 0 : getDepositPercent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}