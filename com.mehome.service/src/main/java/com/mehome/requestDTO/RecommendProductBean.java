package com.mehome.requestDTO;

import org.apache.commons.lang3.StringUtils;

import com.mehome.domain.RecommendProduct;
import com.mehome.utils.PageMysqlUtil;

public class RecommendProductBean extends PageMysqlUtil{
	private Integer recommendId;

    private String img;

    private String title;

    private Integer productId;

    private String description;

    private Integer sort;

    private Boolean active;

    private Boolean top;

    private String location;

    private Boolean showTime;

    private String jumpUrl;

    private String platform;

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getShowTime() {
		return showTime;
	}

	public void setShowTime(Boolean showTime) {
		this.showTime = showTime;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
    
    public RecommendProduct beanToPojo(Boolean addBoolean){
    	RecommendProduct recommend=new RecommendProduct();
    	recommend.setActive(addBoolean?Boolean.TRUE:this.getActive());
    	recommend.setDescription(this.getDescription());
    	recommend.setImg(this.getImg());
    	recommend.setJumpUrl(this.getJumpUrl());
    	recommend.setLocation(StringUtils.isEmpty(this.getLocation())?"app_index_location":this.getLocation());
    	recommend.setPlatform(StringUtils.isEmpty(this.getPlatform())?"Me":this.getPlatform());
    	recommend.setProductId(this.getProductId());
    	recommend.setShowTime(this.getShowTime()==null&&addBoolean?Boolean.FALSE:this.getShowTime());
    	recommend.setSort(this.getSort()==null&&addBoolean?0:this.getSort());
    	recommend.setTitle(this.getTitle());
    	recommend.setTop(this.getTop()==null&&addBoolean?Boolean.FALSE:this.getTop());
    	return recommend;
    }
    
    public RecommendProductBean(){
    	
    }
    
    public RecommendProductBean(RecommendProduct product){
    	this.setActive(product.getActive());
    	this.setDescription(product.getDescription());
    	this.setImg(product.getImg());
    	this.setJumpUrl(product.getJumpUrl());
    	this.setLocation(product.getLocation());
    	this.setPlatform(product.getPlatform());
    	this.setProductId(product.getProductId());
    	this.setRecommendId(product.getRecommendId());
    	this.setShowTime(product.getShowTime());
    	this.setSort(product.getSort());
    	this.setTitle(product.getTitle());
    	this.setTop(product.getTop());
    }
}
