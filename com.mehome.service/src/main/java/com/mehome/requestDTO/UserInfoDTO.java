package com.mehome.requestDTO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;
import com.mehome.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class UserInfoDTO extends PageMysqlUtil {
    private Integer userId;

    private Integer sex;

    private String realName;

    private String mobile;

    private String companyId;

    private Integer companyStatus;

    private String companyName;

    private Date startTime;

    private Date endTime;

    private String namePhoneKeyWords;

    private List<Integer> companyIds = null;

    public List<Integer> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Integer> companyIds) {
        this.companyIds = companyIds;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNamePhoneKeyWords() {
        return namePhoneKeyWords;
    }

    public void setNamePhoneKeyWords(String namePhoneKeyWords) {
        this.namePhoneKeyWords = namePhoneKeyWords;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
