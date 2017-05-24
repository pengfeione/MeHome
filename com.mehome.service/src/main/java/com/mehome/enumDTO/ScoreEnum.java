package com.mehome.enumDTO;

public enum ScoreEnum {
	VERY_SATISFIED(5,"非常满意"),SATISFIED(4,"满意"),NORMAL(3,"一般"),UNSATISFY(2,"不满意"),VERY_UNSATISFY(1,"非常不满意");
	private int key;
	private String value;

	private ScoreEnum(int key, String value) {
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
