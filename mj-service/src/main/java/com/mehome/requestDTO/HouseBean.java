package com.mehome.requestDTO;

import java.util.List;

import com.mehome.utils.PageMysqlUtil;
/**
 * 房源
 * @author xuyixun21
 *
 */
public class HouseBean extends PageMysqlUtil{

	private Integer houseId;
	
	private Integer areaId;
	
	private Integer supplierId;
	
	private String subject;
	
	private String summary;
	
	private String detail;
	
	private String address;
	
	private String listpic;
	
	private List<String> detailpic;
	
	private String position;
	
	private Integer sort;
	
	private Integer roomType;
	
	private String roomTypeDesc;
	
	private Double roomArea;
	
	private Integer roomRent;
	
	private String payType;
	
	private Integer status;
	
	private String startTime;
	
	private String endTime;
	
	private String leaseHolder;
	
	
}
