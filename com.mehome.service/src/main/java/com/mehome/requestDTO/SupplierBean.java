package com.mehome.requestDTO;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.mehome.domain.SupplierList;
import com.mehome.utils.DateUtils;
import com.mehome.utils.PageMysqlUtil;

/**
 * 供应商
 * @author xuyixun21
 *
 */
public class SupplierBean extends PageMysqlUtil{

	private Integer supplierId;
	
	private String supplierName;
	
	private String supplierPhone;
	
	private String recipientType;
	
	private String recipientAccount;
	
	private Boolean rentOnline;
	
	private BigDecimal rentPercent;

    private Boolean depositOnline;

    private BigDecimal depositPercent;

    private String createTime;

    private String updateTime;

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

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getRecipientAccount() {
		return recipientAccount;
	}

	public void setRecipientAccount(String recipientAccount) {
		this.recipientAccount = recipientAccount;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public SupplierList beanToPojo(Boolean addBoolean){
		SupplierList supplier = new SupplierList();
		Date newDate = new Date();
		supplier.setSupplierId(this.getSupplierId());
		supplier.setCreateTime(addBoolean?newDate:null);
		supplier.setDepositOnline(this.getDepositOnline()==null&&addBoolean?Boolean.FALSE:this.getDepositOnline());
		supplier.setDepositPercent(this.getDepositPercent()==null&&addBoolean?new BigDecimal("0.00"):this.getDepositPercent());
		supplier.setRecipientAccount(this.getRecipientAccount());
		supplier.setRecipientType(this.getRecipientType());
		supplier.setRentOnline(this.getRentOnline()==null&&addBoolean?Boolean.FALSE:this.getRentOnline());
		supplier.setRentPercent(this.getRentPercent()==null&&addBoolean?new BigDecimal("0.00"):this.getRentPercent());
		supplier.setSupplierName(this.getSupplierName());
		supplier.setSupplierPhone(StringUtils.isEmpty(this.getSupplierPhone())?"联系电话未定义":this.getSupplierPhone());
		supplier.setUpdateTime(newDate);
		return supplier;
	}
	
	public SupplierBean(){
		
	}
	public SupplierBean(SupplierList supplier){
		this.setCreateTime(supplier.getCreateTime()==null?null:DateUtils.dateToStr(supplier.getCreateTime()));
		this.setDepositOnline(supplier.getDepositOnline());
		this.setDepositPercent(supplier.getDepositPercent());
		this.setRecipientAccount(supplier.getRecipientAccount());
		this.setRecipientType(supplier.getRecipientType());
		this.setRentOnline(supplier.getRentOnline());
		this.setRentPercent(supplier.getRentPercent());
		this.setSupplierId(supplier.getSupplierId());
		this.setSupplierName(supplier.getSupplierName());
		this.setSupplierPhone(supplier.getSupplierPhone());
		this.setUpdateTime(supplier.getUpdateTime()==null?null:DateUtils.dateToStr(supplier.getUpdateTime()));
	}
}
