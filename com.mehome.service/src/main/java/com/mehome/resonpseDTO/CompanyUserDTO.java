package com.mehome.resonpseDTO;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/12.
 */
public class CompanyUserDTO {
    private Integer userId;
    private String name;
    private String mobile;
    private String idCard;
    private Date submitApplication;
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getSubmitApplication() {
        return submitApplication;
    }

    public void setSubmitApplication(Date submitApplication) {
        this.submitApplication = submitApplication;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
