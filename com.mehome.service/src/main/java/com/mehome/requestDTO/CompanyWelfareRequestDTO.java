package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/24.
 */
public class CompanyWelfareRequestDTO {
    private Integer welfareId;

    private Integer companyId;

    private Integer productId;

    private Boolean isSelect;

    private Boolean welfareActive;

    public Integer getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Integer welfareId) {
        this.welfareId = welfareId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    public Boolean getWelfareActive() {
        return welfareActive;
    }

    public void setWelfareActive(Boolean welfareActive) {
        this.welfareActive = welfareActive;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
