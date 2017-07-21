package com.mehome.enumDTO;

/**
 * Created by trancy on 2017/7/20.
 */
public enum TradeType {
    JSAPI("JSAPI", "公众号支付"), NATIVE("NATIVE", "原生扫码支付"), APP("APP", "app支付");
    private String name;
    private String desc;

    TradeType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
