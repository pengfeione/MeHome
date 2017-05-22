package com.mehome.enumDTO;

/**
 * Created by pengfei on 2017/5/18.
 */
public enum TemplateCodeEnum  {

    REGISTER_MSG("SMS_67245574", "用户注册短信"),RESET_MSG("SMS_67195847", "用户密码找回短信");
    private String key;
    private String value;

    private TemplateCodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
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

}
