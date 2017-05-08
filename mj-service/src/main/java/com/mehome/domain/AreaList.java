package com.mehome.domain;

import java.util.Date;

public class AreaList {
    private Integer areaId;

    private String areaPostCode;

    private Date updateTime;

    private Integer cityId;

    private String areaName;

    private Boolean areaActive;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaPostCode() {
        return areaPostCode;
    }

    public void setAreaPostCode(String areaPostCode) {
        this.areaPostCode = areaPostCode == null ? null : areaPostCode.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Boolean getAreaActive() {
        return areaActive;
    }

    public void setAreaActive(Boolean areaActive) {
        this.areaActive = areaActive;
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
        AreaList other = (AreaList) that;
        return (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getAreaPostCode() == null ? other.getAreaPostCode() == null : this.getAreaPostCode().equals(other.getAreaPostCode()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getAreaName() == null ? other.getAreaName() == null : this.getAreaName().equals(other.getAreaName()))
            && (this.getAreaActive() == null ? other.getAreaActive() == null : this.getAreaActive().equals(other.getAreaActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getAreaPostCode() == null) ? 0 : getAreaPostCode().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getAreaName() == null) ? 0 : getAreaName().hashCode());
        result = prime * result + ((getAreaActive() == null) ? 0 : getAreaActive().hashCode());
        return result;
    }
}