package com.mehome.enumDTO;

public enum HouseStatusEnum {

	// 1可用、2已租借、0已下线
	OFFLINE(0, "已下线"), AVAILABLE(1, "可用"), LEASED(2, "已租借");

	private int key;
	private String value;

	private HouseStatusEnum(int key, String value) {
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
