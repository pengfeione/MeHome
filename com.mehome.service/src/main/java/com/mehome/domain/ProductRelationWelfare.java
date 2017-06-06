package com.mehome.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class ProductRelationWelfare {
    private Integer welfareId;

    private Integer productId;

    private Date createTime;

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
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}