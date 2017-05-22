package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mehome.exceptions.InfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by renhui on 2017/5/8.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private int code;
    private String msg;
    private String requestId;
    private T result;
    private Long totalCount;


    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.requestId = UUID.randomUUID().toString();
    }

    public static Result build() {
        Result result = new Result();
        result.code = 0;
        result.msg = "success";
        result.requestId = UUID.randomUUID().toString();
        return result;
    }

    public Result() {
    }

    public static Result buildError(String msg) {
        return new Result(400, msg);
    }

    public Result content(T object, Long totalCount) {
        this.setResult(object);
        this.setTotalCount(totalCount);
        return this;
    }

    public Result content(T object) {
        return content(object, null);
    }

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
