package com.mehome.resonpseDTO;

import com.alibaba.fastjson.JSONObject;

public class HouseTimePiece {
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
