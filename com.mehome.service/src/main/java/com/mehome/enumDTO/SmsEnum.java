package com.mehome.enumDTO;

/**
 * Created by Administrator on 2017/5/11.
 */
public enum SmsEnum {
    //  类型（0注册普通用户，1修改密码，2短信登录）
    NORMAL_REG(0, "注册普通用户", "米加欢迎登录"),
    NORMAL_FIX_PASSWORD(1, "普通用户修改密码", ""),
    NORMAL_LOGIN(2, "普通用户短信登录", "");
    private int key;

    private String value;

    private String pattern;

    private SmsEnum(int key, String value, String pattern) {
        this.key = key;
        this.value = value;
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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
