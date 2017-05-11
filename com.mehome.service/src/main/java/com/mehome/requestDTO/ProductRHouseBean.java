package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;

public class ProductRHouseBean extends PageMysqlUtil {

	private Integer productId;
	
	private Integer houseId;
	
	private String ralationId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getRalationId() {
		return ralationId;
	}

	public void setRalationId(String ralationId) {
		this.ralationId = ralationId;
	}
	
	
}
