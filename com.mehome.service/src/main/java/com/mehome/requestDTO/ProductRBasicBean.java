package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;

public class ProductRBasicBean extends PageMysqlUtil {

	private Integer productId;
	
	private Integer basicId;
	
	private String basicType;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getBasicId() {
		return basicId;
	}

	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}

	public String getBasicType() {
		return basicType;
	}

	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}
	
	
}
