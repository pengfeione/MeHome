package com.mehome.enumDTO;

import com.mehome.domain.SmsRecord;
import com.mehome.utils.AliyuncsSMSTemp;

/**
 * Created by Administrator on 2017/5/11.
 */
public enum SmsEnum {
    //  类型（0注册普通用户，1修改密码，2短信登录）
    NORMAL_REG(0, "普通短信", "SMS_67195847"),
    NORMAL_FIX_PASSWORD(1, "找回密码", "SMS_67245574"),
    NORMAL_LOGIN(2, "找回密码", "SMS_67245574");
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


    public static SmsEnum getByKey(Integer key) {
        SmsEnum[] enums = SmsEnum.values();
        for (SmsEnum item : enums
                ) {
            if (item.getKey() == key) {
                return item;
            }
        }
        return null;
    }
}
