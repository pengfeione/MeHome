package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/5/22.
 */
public class UserApplyCompanyDTO {
    private Integer userId;//用户ID
    private String authCode;//授权码
    private String realName;//用户真实姓名
    private String idCard;//什么证号

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
