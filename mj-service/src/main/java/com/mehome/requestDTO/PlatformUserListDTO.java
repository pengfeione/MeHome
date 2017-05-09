package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

import java.util.Date;

/**
 * Created by renhui on 2017/5/8.
 */
public class PlatformUserListDTO extends PageMysqlUtil {
    private String userId;
    private String name;
    private String phone;
    private String company;
    private String userType;//企业用户，个人用户
    private Date regStart;
    private Date regEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getRegStart() {
        return regStart;
    }

    public void setRegStart(Date regStart) {
        this.regStart = regStart;
    }

    public Date getRegEnd() {
        return regEnd;
    }

    public void setRegEnd(Date regEnd) {
        this.regEnd = regEnd;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
