package com.mehome.domain;

import java.util.Date;

public class ProductRalationWelfare {
    private String ralationId;

    private Integer welfareId;

    private Integer productId;

    private Date createTime;

    public String getRalationId() {
        return ralationId;
    }

    public void setRalationId(String ralationId) {
        this.ralationId = ralationId == null ? null : ralationId.trim();
    }

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        ProductRalationWelfare other = (ProductRalationWelfare) that;
        return (this.getRalationId() == null ? other.getRalationId() == null : this.getRalationId().equals(other.getRalationId()))
            && (this.getWelfareId() == null ? other.getWelfareId() == null : this.getWelfareId().equals(other.getWelfareId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRalationId() == null) ? 0 : getRalationId().hashCode());
        result = prime * result + ((getWelfareId() == null) ? 0 : getWelfareId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}