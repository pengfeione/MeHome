package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;

public class ProductRWelfareBean extends PageMysqlUtil {

	private Integer welfareId;
	
	private Integer productId;
	
	private String ralationId;

	public Integer getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(Integer welfareId) {
		this.welfareId = welfareId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getRalationId() {
		return ralationId;
	}

	public void setRalationId(String ralationId) {
		this.ralationId = ralationId;
	}
	
	
}
