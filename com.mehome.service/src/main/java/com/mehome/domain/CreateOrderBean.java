package com.mehome.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by trancy on 2017/7/21.
 */
public class CreateOrderBean {
    private String orderId;
    private String tradeType;
    private String payer;
    private String payType;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
