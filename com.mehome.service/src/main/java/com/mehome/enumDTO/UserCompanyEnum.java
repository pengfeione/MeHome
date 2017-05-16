package com.mehome.enumDTO;

/**
 * Created by Administrator on 2017/5/16.
 */
public enum UserCompanyEnum {
    DISMISS(0, "关系已解除"), ACTIVE(1, "正常"), WAITING(2, "待审核"), REFUSE(3, "审核不通过");
    private Integer key;
    private String value;

    private UserCompanyEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
