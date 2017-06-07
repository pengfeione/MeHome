package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

public class ProductCompanyWelfareDTO extends PageMysqlUtil {
    private Integer productId;
    private String companyWelfareIds;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCompanyWelfareIds() {
        return companyWelfareIds;
    }

    public void setCompanyWelfareIds(String companyWelfareIds) {
        this.companyWelfareIds = companyWelfareIds;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
