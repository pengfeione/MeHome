package com.mehome.enumDTO;

public enum OrderStatusEnum {

//0预约   1确认中 2已经确认 3入住  4结束 5取消
	ORDER(0, "预约"), CONFIRMING(1,"确认中"),CONFIRMED(2, "已经确认"),CHECKIN(3,"入住"),END(4,"结束"),CANCEL(5,"取消");

    private int key;
    private String value;

    private OrderStatusEnum(int key, String value) {
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
