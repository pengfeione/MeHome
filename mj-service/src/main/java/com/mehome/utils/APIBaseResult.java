package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

/**
 * Created by pengf on 2015/11/26.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class APIBaseResult {
    private int code;
    private String msg;
    private String requestId;
    private Object result;
    private Long totalCount;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public APIBaseResult() {
        this.requestId = UUID.randomUUID().toString();
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
