package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import com.mehome.exceptions.InfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by renhui on 2017/5/8.
 */
public class Result {
    protected final Logger logger = LoggerFactory.getLogger("Request");
    private int code;
    private String msg;
    private String requestId;
    private Object result;
    private Long totalCount;
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    private Result(String log) {
        this.log = log;
        this.code=0;
        this.msg="success";
        this.requestId = UUID.randomUUID().toString();
    }
    public static Result build(String log){
        return new Result(log);
    }
    public Result content(Object object,Long totalCount){
        try{
            this.setResult(object);
            if(null!=totalCount){
                this.setTotalCount(totalCount);
            }
        }catch (Exception e){
            this.setCode(400);
            if(e instanceof InfoException){
                this.setMsg(e.getMessage());
            }else{
                this.setMsg("服务器异常!");
            }
        }finally {
            logger.info(log+"\n"+this);
            return this;
        }
    }
    public Result content(Object object){
        return content(object,null);
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
