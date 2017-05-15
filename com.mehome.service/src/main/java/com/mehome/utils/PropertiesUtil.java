package com.mehome.utils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/2.
 */
@Component
@ConfigurationProperties(prefix = "thirdpay")
public class PropertiesUtil {
    private String iface;
    private String impl;
	public String getIface() {
		return iface;
	}
	public void setIface(String iface) {
		this.iface = iface;
	}
	public String getImpl() {
		return impl;
	}
	public void setImpl(String impl) {
		this.impl = impl;
	}
}
