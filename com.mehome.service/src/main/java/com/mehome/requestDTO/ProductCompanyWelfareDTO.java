package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

import java.util.List;

public class ProductCompanyWelfareDTO extends PageMysqlUtil {
    private Integer productId;
    private Boolean add;
    private String companyWelfareIds;

    private List<Integer> dontNeedCompanyId;

    public List<Integer> getDontNeedCompanyId() {
        return dontNeedCompanyId;
    }

    public void setDontNeedCompanyId(List<Integer> dontNeedCompanyId) {
        this.dontNeedCompanyId = dontNeedCompanyId;
    }

    public Boolean getAdd() {
        return add;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }

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
