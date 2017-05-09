package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

/**
 * Created by renhui on 2017/5/8.
 */
public class CompanyUserListDTO extends PageMysqlUtil {
    private String authStatus;
    private String key;
    private String orderBy;

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
