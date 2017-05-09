package com.mehome.requestDTO;

import java.math.BigDecimal;
import java.util.Date;

import com.mehome.utils.PageMysqlUtil;
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
	
	
	
	
	

}
