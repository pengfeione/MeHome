package com.mehome.service.weixin;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WXResult<T> {
	private String return_code;
	private String return_msg;
	public static WXResult build() {
		WXResult result = new WXResult();
        result.return_code = "SUCCESS";
        result.return_msg = "支付通知已接收";
        return result;
    }
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

}
