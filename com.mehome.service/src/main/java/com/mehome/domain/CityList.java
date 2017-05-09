package com.mehome.domain;

import java.util.Date;

public class CityList {
    private Integer cityId;

    private String cityName;

    private Date updateTime;

    private String cityHeadCode;

    private String cityPinyin;

    private String postCode;

    private String belongCountry;

    private Boolean cityActive;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityHeadCode() {
        return cityHeadCode;
    }

    public void setCityHeadCode(String cityHeadCode) {
        this.cityHeadCode = cityHeadCode == null ? null : cityHeadCode.trim();
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin == null ? null : cityPinyin.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getBelongCountry() {
        return belongCountry;
    }

    public void setBelongCountry(String belongCountry) {
        this.belongCountry = belongCountry == null ? null : belongCountry.trim();
    }

    public Boolean getCityActive() {
        return cityActive;
    }

    public void setCityActive(Boolean cityActive) {
        this.cityActive = cityActive;
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
        CityList other = (CityList) that;
        return (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCityHeadCode() == null ? other.getCityHeadCode() == null : this.getCityHeadCode().equals(other.getCityHeadCode()))
            && (this.getCityPinyin() == null ? other.getCityPinyin() == null : this.getCityPinyin().equals(other.getCityPinyin()))
            && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode()))
            && (this.getBelongCountry() == null ? other.getBelongCountry() == null : this.getBelongCountry().equals(other.getBelongCountry()))
            && (this.getCityActive() == null ? other.getCityActive() == null : this.getCityActive().equals(other.getCityActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCityHeadCode() == null) ? 0 : getCityHeadCode().hashCode());
        result = prime * result + ((getCityPinyin() == null) ? 0 : getCityPinyin().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getBelongCountry() == null) ? 0 : getBelongCountry().hashCode());
        result = prime * result + ((getCityActive() == null) ? 0 : getCityActive().hashCode());
        return result;
    }
}