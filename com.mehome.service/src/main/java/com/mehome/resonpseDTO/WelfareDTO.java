package com.mehome.resonpseDTO;

import com.alibaba.fastjson.JSONObject;

public class WelfareDTO {
    private Integer mortagageNum;
    private String name;
    private String notice;
    private Integer payMentNum;
    private Integer remitPercent;

    public Integer getMortagageNum() {
        return mortagageNum;
    }

    public void setMortagageNum(Integer mortagageNum) {
        this.mortagageNum = mortagageNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getPayMentNum() {
        return payMentNum;
    }

    public void setPayMentNum(Integer payMentNum) {
        this.payMentNum = payMentNum;
    }

    public Integer getRemitPercent() {
        return remitPercent;
    }

    public void setRemitPercent(Integer remitPercent) {
        this.remitPercent = remitPercent;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
