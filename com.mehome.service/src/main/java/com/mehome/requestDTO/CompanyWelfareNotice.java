package com.mehome.requestDTO;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/5/22.
 */
public class CompanyWelfareNotice {
//    {"notice":"公告","remitPercent":"减免百分比","payMentNum":m,"mortagageNum":n}

    private String notice;
    private Integer remitPercent = 0;
    private Integer payMentNum = 0;
    private Integer mortagageNum = 0;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getRemitPercent() {
        return remitPercent;
    }

    public void setRemitPercent(Integer remitPercent) {
        this.remitPercent = remitPercent;
    }

    public Integer getPayMentNum() {
        return payMentNum;
    }

    public void setPayMentNum(Integer payMentNum) {
        this.payMentNum = payMentNum;
    }

    public Integer getMortagageNum() {
        return mortagageNum;
    }

    public void setMortagageNum(Integer mortagageNum) {
        this.mortagageNum = mortagageNum;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
