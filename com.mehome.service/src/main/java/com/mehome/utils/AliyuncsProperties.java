package com.mehome.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pengfei on 2017/5/11.
 */
@Component
@ConfigurationProperties(prefix = "aliyuncs")

public class AliyuncsProperties {

    private String accessid;
    private String accessKey;
    private String oss_endpoint;
    private String oss_bucket;
    private String oss_dir;
    private String sms_topic;
    private String sms_endpoint;


    public String getSms_endpoint() {
        return sms_endpoint;
    }

    public void setSms_endpoint(String sms_endpoint) {
        this.sms_endpoint = sms_endpoint;
    }


    public String getAccessid() {
        return accessid;
    }

    public void setAccessid(String accessid) {
        this.accessid = accessid;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getOss_endpoint() {
        return oss_endpoint;
    }

    public void setOss_endpoint(String oss_endpoint) {
        this.oss_endpoint = oss_endpoint;
    }

    public String getOss_bucket() {
        return oss_bucket;
    }

    public void setOss_bucket(String oss_bucket) {
        this.oss_bucket = oss_bucket;
    }

    public String getOss_dir() {
        return oss_dir;
    }

    public void setOss_dir(String oss_dir) {
        this.oss_dir = oss_dir;
    }

    public String getSms_topic() {
        return sms_topic;
    }

    public void setSms_topic(String sms_topic) {
        this.sms_topic = sms_topic;
    }


}
