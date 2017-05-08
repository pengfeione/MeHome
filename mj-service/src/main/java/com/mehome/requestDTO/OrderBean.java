package com.mehome.requestDTO;

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
	
	

}
