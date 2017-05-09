package com.mehome.domain;

import java.util.Date;

public class ProductRalationBasic {
    private String ralationId;

    private Integer productId;

    private Integer basicId;

    private String basicType;

    private Date createTime;

    public String getRalationId() {
        return ralationId;
    }

    public void setRalationId(String ralationId) {
        this.ralationId = ralationId == null ? null : ralationId.trim();
    }

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
        this.basicType = basicType == null ? null : basicType.trim();
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
        ProductRalationBasic other = (ProductRalationBasic) that;
        return (this.getRalationId() == null ? other.getRalationId() == null : this.getRalationId().equals(other.getRalationId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getBasicId() == null ? other.getBasicId() == null : this.getBasicId().equals(other.getBasicId()))
            && (this.getBasicType() == null ? other.getBasicType() == null : this.getBasicType().equals(other.getBasicType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRalationId() == null) ? 0 : getRalationId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getBasicId() == null) ? 0 : getBasicId().hashCode());
        result = prime * result + ((getBasicType() == null) ? 0 : getBasicType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}