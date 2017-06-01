package com.mehome.enumDTO;

public enum PayTypeEnum {
    ALIPAY("alipay", "支付宝"), WECHAT("wechat", "微信支付"), CARD("creditcard", "信用卡支付"), CASH("cash", "现金支付");
    private String key;
    private String value;

    private PayTypeEnum(String key, String value) {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean contain(String key) {
        PayTypeEnum[] payTypeEnums = PayTypeEnum.values();
        for (PayTypeEnum item : payTypeEnums) {
            if (item.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
}
