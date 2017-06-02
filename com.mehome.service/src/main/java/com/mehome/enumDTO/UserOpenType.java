package com.mehome.enumDTO;

public enum UserOpenType {
    MOBILE(0, "手机号"), WECHAT(1, "微信账号");

    private int key;
    private String value;

    private UserOpenType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
