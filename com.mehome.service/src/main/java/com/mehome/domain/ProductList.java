package com.mehome.domain;

import java.util.Date;

public class ProductList {
    private Integer productId;

    private Integer supplierId;

    private Integer areaId;

    private String productName;

    private String productDetail;

    private Integer productSort;

    private String listpic;

    private String detailpic;

    private String address;

    private String position;

    private Boolean productActive;

    private String rules;

    private Integer welfareId;

    private Boolean isWelfare;

    private String personalWelfare;

    private Boolean hasPersonal;

    private String netRulesUrl;
    
    private Integer floor;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Float score;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail == null ? null : productDetail.trim();
    }

    public Integer getProductSort() {
        return productSort;
    }

    public void setProductSort(Integer productSort) {
        this.productSort = productSort;
    }

    public String getListpic() {
        return listpic;
    }

    public void setListpic(String listpic) {
        this.listpic = listpic == null ? null : listpic.trim();
    }

    public String getDetailpic() {
        return detailpic;
    }

    public void setDetailpic(String detailpic) {
        this.detailpic = detailpic == null ? null : detailpic.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Boolean getProductActive() {
        return productActive;
    }

    public void setProductActive(Boolean productActive) {
        this.productActive = productActive;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

    public Boolean getIsWelfare() {
        return isWelfare;
    }

    public void setIsWelfare(Boolean isWelfare) {
        this.isWelfare = isWelfare;
    }

    public String getPersonalWelfare() {
        return personalWelfare;
    }

    public void setPersonalWelfare(String personalWelfare) {
        this.personalWelfare = personalWelfare == null ? null : personalWelfare.trim();
    }

    public Boolean getHasPersonal() {
        return hasPersonal;
    }

    public void setHasPersonal(Boolean hasPersonal) {
        this.hasPersonal = hasPersonal;
    }

    public String getNetRulesUrl() {
        return netRulesUrl;
    }

    public void setNetRulesUrl(String netRulesUrl) {
        this.netRulesUrl = netRulesUrl == null ? null : netRulesUrl.trim();
    }

    
    public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductList other = (ProductList) that;
        return (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductDetail() == null ? other.getProductDetail() == null : this.getProductDetail().equals(other.getProductDetail()))
            && (this.getProductSort() == null ? other.getProductSort() == null : this.getProductSort().equals(other.getProductSort()))
            && (this.getListpic() == null ? other.getListpic() == null : this.getListpic().equals(other.getListpic()))
            && (this.getDetailpic() == null ? other.getDetailpic() == null : this.getDetailpic().equals(other.getDetailpic()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getProductActive() == null ? other.getProductActive() == null : this.getProductActive().equals(other.getProductActive()))
            && (this.getRules() == null ? other.getRules() == null : this.getRules().equals(other.getRules()))
            && (this.getWelfareId() == null ? other.getWelfareId() == null : this.getWelfareId().equals(other.getWelfareId()))
            && (this.getIsWelfare() == null ? other.getIsWelfare() == null : this.getIsWelfare().equals(other.getIsWelfare()))
            && (this.getPersonalWelfare() == null ? other.getPersonalWelfare() == null : this.getPersonalWelfare().equals(other.getPersonalWelfare()))
            && (this.getHasPersonal() == null ? other.getHasPersonal() == null : this.getHasPersonal().equals(other.getHasPersonal()))
            && (this.getNetRulesUrl() == null ? other.getNetRulesUrl() == null : this.getNetRulesUrl().equals(other.getNetRulesUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductDetail() == null) ? 0 : getProductDetail().hashCode());
        result = prime * result + ((getProductSort() == null) ? 0 : getProductSort().hashCode());
        result = prime * result + ((getListpic() == null) ? 0 : getListpic().hashCode());
        result = prime * result + ((getDetailpic() == null) ? 0 : getDetailpic().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getProductActive() == null) ? 0 : getProductActive().hashCode());
        result = prime * result + ((getRules() == null) ? 0 : getRules().hashCode());
        result = prime * result + ((getWelfareId() == null) ? 0 : getWelfareId().hashCode());
        result = prime * result + ((getIsWelfare() == null) ? 0 : getIsWelfare().hashCode());
        result = prime * result + ((getPersonalWelfare() == null) ? 0 : getPersonalWelfare().hashCode());
        result = prime * result + ((getHasPersonal() == null) ? 0 : getHasPersonal().hashCode());
        result = prime * result + ((getNetRulesUrl() == null) ? 0 : getNetRulesUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        return result;
    }
}