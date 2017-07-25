package com.mehome.enumDTO;

public enum PayStatusEnum {
	    //1:下单成功  2：下单失败  3 支付成功 4 支付失败
		ORDER_SUCCESS(1, "下单成功"), ORDER_FAIL(2,"下单失败"),PAY_SUCCESS(3, "支付成功"),PAY_FAIL(4,"支付失败");

	    private int key;
	    private String value;

	    private PayStatusEnum(int key, String value) {
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
