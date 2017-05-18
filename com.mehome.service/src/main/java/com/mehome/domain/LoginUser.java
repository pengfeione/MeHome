package com.mehome.domain;

import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/18.
 */
public class LoginUser {
    private Integer userId;

    private Integer userAmount;

    private String wechatId;

    private String nickName;

    private String avatar;

    private Integer sex;

    private String realName;

    private String mobile;

    private Integer companyId;

    private Integer companyStatus;

    public LoginUser() {
    }

    public LoginUser(UserInfo userInfo) {
        BeanUtils.copyProperties(userInfo, this);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Integer userAmount) {
        this.userAmount = userAmount;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }
}
