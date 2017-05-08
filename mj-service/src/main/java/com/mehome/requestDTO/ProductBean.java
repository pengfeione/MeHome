package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;
/**
 * 产品
 * @author xuyixun21
 *
 */
public class ProductBean extends PageMysqlUtil{
	

	private Integer productId;
	
	private String productName;
	
	private Integer supplierId;
	
	private Integer areaId;
	
	private Boolean productActive;
	
	private Integer productSort;
	/**
	 * 是否有个人福利
	 */
	private Boolean hasPersonal;
	/**
	 * 是否有企业福利
	 */
	private Boolean isWelfare;
	
	private String productDetail;
	
	private String listpic;
	
	private String detailpic;
	
	private String address;
	
	private String position;
	
	private String rules;
	
	private String netRulesUrl;
}
