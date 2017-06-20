package com.mehome.domain;

import com.alibaba.fastjson.JSONObject;

public class CompanyWelfareDTO {
    private Integer productId;
    private Integer companyId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
