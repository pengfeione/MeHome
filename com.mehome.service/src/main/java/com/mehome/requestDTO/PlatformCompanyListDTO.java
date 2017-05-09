package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

import java.util.Date;

/**
 * Created by renhui on 2017/5/8.
 */
public class PlatformCompanyListDTO extends PageMysqlUtil {
    private String name;
    private String hasWelfare;
    private Date regStart;
    private Date regEnd;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHasWelfare() {
        return hasWelfare;
    }

    public void setHasWelfare(String hasWelfare) {
        this.hasWelfare = hasWelfare;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
