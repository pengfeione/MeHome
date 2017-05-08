package com.mehome.domain;

import java.util.Date;

public class CompanyWelfare {
    private Integer welfareId;

    private String welfareContent;

    private Integer companyId;

    private Boolean isSelect;

    private Boolean welfareActive;

    private Date updateTime;

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

    public String getWelfareContent() {
        return welfareContent;
    }

    public void setWelfareContent(String welfareContent) {
        this.welfareContent = welfareContent == null ? null : welfareContent.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Boolean getWelfareActive() {
        return welfareActive;
    }

    public void setWelfareActive(Boolean welfareActive) {
        this.welfareActive = welfareActive;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        CompanyWelfare other = (CompanyWelfare) that;
        return (this.getWelfareId() == null ? other.getWelfareId() == null : this.getWelfareId().equals(other.getWelfareId()))
            && (this.getWelfareContent() == null ? other.getWelfareContent() == null : this.getWelfareContent().equals(other.getWelfareContent()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getIsSelect() == null ? other.getIsSelect() == null : this.getIsSelect().equals(other.getIsSelect()))
            && (this.getWelfareActive() == null ? other.getWelfareActive() == null : this.getWelfareActive().equals(other.getWelfareActive()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWelfareId() == null) ? 0 : getWelfareId().hashCode());
        result = prime * result + ((getWelfareContent() == null) ? 0 : getWelfareContent().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getIsSelect() == null) ? 0 : getIsSelect().hashCode());
        result = prime * result + ((getWelfareActive() == null) ? 0 : getWelfareActive().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}