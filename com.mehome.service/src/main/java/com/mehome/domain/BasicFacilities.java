package com.mehome.domain;

import java.util.Date;

public class BasicFacilities {
    private Integer basicId;

    private String pic;

    private String basicName;

    private String basicType;

    private Date updateTime;

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
        this.pic = pic == null ? null : pic.trim();
    }

    public String getBasicName() {
        return basicName;
    }

    public void setBasicName(String basicName) {
        this.basicName = basicName == null ? null : basicName.trim();
    }

    public String getBasicType() {
        return basicType;
    }

    public void setBasicType(String basicType) {
        this.basicType = basicType == null ? null : basicType.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getBasicActive() {
        return basicActive;
    }

    public void setBasicActive(Boolean basicActive) {
        this.basicActive = basicActive;
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
        BasicFacilities other = (BasicFacilities) that;
        return (this.getBasicId() == null ? other.getBasicId() == null : this.getBasicId().equals(other.getBasicId()))
            && (this.getPic() == null ? other.getPic() == null : this.getPic().equals(other.getPic()))
            && (this.getBasicName() == null ? other.getBasicName() == null : this.getBasicName().equals(other.getBasicName()))
            && (this.getBasicType() == null ? other.getBasicType() == null : this.getBasicType().equals(other.getBasicType()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getBasicActive() == null ? other.getBasicActive() == null : this.getBasicActive().equals(other.getBasicActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBasicId() == null) ? 0 : getBasicId().hashCode());
        result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
        result = prime * result + ((getBasicName() == null) ? 0 : getBasicName().hashCode());
        result = prime * result + ((getBasicType() == null) ? 0 : getBasicType().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getBasicActive() == null) ? 0 : getBasicActive().hashCode());
        return result;
    }
}