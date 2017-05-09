package com.mehome.requestDTO;

import java.util.Date;

import com.mehome.domain.BasicFacilities;
import com.mehome.utils.DateUtils;

public class BasicBean {

	private Integer basicId;
	
	private String pic;
	
	private String basicName;
	
	private String basicType;
	
	private String updateTime;
	
	private Boolean basicActive;
	
	
	public Integer getBasicId() {
		return basicId;
	}


	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public String getBasicName() {
		return basicName;
	}


	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}


	public String getBasicType() {
		return basicType;
	}


	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public Boolean getBasicActive() {
		return basicActive;
	}


	public void setBasicActive(Boolean basicActive) {
		this.basicActive = basicActive;
	}


	public BasicFacilities beanToPojo(){
		BasicFacilities basic = new BasicFacilities();
		basic.setBasicActive(Boolean.TRUE);
		basic.setBasicName(this.getBasicName());
		basic.setBasicType(this.getBasicType());
		basic.setPic(this.getPic());
		basic.setUpdateTime(new Date());
		return basic;
	}
	
	public BasicBean(BasicFacilities pojo){
		this.setBasicActive(pojo.getBasicActive());
		this.setBasicId(pojo.getBasicId());
		this.setBasicName(pojo.getBasicName());
		this.setBasicType(pojo.getBasicType());
		this.setPic(pojo.getPic());
		this.setUpdateTime(DateUtils.dateToStr(pojo.getUpdateTime()));
	}
}
