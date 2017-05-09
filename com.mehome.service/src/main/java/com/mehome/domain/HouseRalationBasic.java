package com.mehome.domain;

import java.util.Date;

public class HouseRalationBasic {
    private String ralationId;

    private Integer basicId;

    private Integer houseId;

    private Date createTime;

    public String getRalationId() {
        return ralationId;
    }

    public void setRalationId(String ralationId) {
        this.ralationId = ralationId == null ? null : ralationId.trim();
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        HouseRalationBasic other = (HouseRalationBasic) that;
        return (this.getRalationId() == null ? other.getRalationId() == null : this.getRalationId().equals(other.getRalationId()))
            && (this.getBasicId() == null ? other.getBasicId() == null : this.getBasicId().equals(other.getBasicId()))
            && (this.getHouseId() == null ? other.getHouseId() == null : this.getHouseId().equals(other.getHouseId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRalationId() == null) ? 0 : getRalationId().hashCode());
        result = prime * result + ((getBasicId() == null) ? 0 : getBasicId().hashCode());
        result = prime * result + ((getHouseId() == null) ? 0 : getHouseId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}