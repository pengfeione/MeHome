package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.PageMysqlUtil;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17.
 */
public class CompanyDTO extends PageMysqlUtil {
    private Integer companyId;
    private Integer registerNumTop;
    private Integer registerNumBottom;
    private String companyName;
    private String companyCode;
    private Boolean companyActive;
    private Date startTime;
    private Date endTime;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRegisterNumTop() {
        return registerNumTop;
    }

    public void setRegisterNumTop(Integer registerNumTop) {
        this.registerNumTop = registerNumTop;
    }

    public Integer getRegisterNumBottom() {
        return registerNumBottom;
    }

    public void setRegisterNumBottom(Integer registerNumBottom) {
        this.registerNumBottom = registerNumBottom;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Boolean getCompanyActive() {
        return companyActive;
    }

    public void setCompanyActive(Boolean companyActive) {
        this.companyActive = companyActive;
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
