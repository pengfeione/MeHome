package com.mehome.enumDTO;

public enum TopicTypeEnum {
	//一般:normal 活动:activity 专题 topic
	NORMAL("normal", "一般资讯"), ACTIVITY("activity", "活动"), TOPIC("topic", "专题");
    private String key;
    private String value;

    private TopicTypeEnum(String key, String value) {
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
