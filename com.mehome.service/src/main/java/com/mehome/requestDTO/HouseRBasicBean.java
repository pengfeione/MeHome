package com.mehome.requestDTO;

import com.mehome.utils.PageMysqlUtil;

public class HouseRBasicBean extends PageMysqlUtil {
	
	private Integer basicId;
	
	private Integer houseId;
	
	private String ralationId;

	public Integer getBasicId() {
		return basicId;
	}

	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
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
