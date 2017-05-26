package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016-03-18.
 */
@Component
@ConfigurationProperties(prefix = "send.weixin")
public class WeChatProperties {
    private String customsend;
    private String templatesend;
    private String sendurl;
    private String tokenurl;

    public String getUserinfourl() {
        return userinfourl;
    }

    public void setUserinfourl(String userinfourl) {
        this.userinfourl = userinfourl;
    }

    private String userinfourl;
    public String getTokenurl() {
        return tokenurl;
    }

    public void setTokenurl(String tokenurl) {
        this.tokenurl = tokenurl;
    }

    private String appid;
    private String secret;
    private String grant_type;
    private String basetokenurl;
    private String granttype;
    private String ticketurl;
    private String pay_unifiedorder;

    public String getCustomsend() {
        return customsend;
    }

    public void setCustomsend(String customsend) {
        this.customsend = customsend;
    }

    public String getTemplatesend() {
        return templatesend;
    }

    public void setTemplatesend(String templatesend) {
        this.templatesend = templatesend;
    }

    public String getSendurl() {
        return sendurl;
    }

    public void setSendurl(String sendurl) {
        this.sendurl = sendurl;
    }




    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getBasetokenurl() {
        return basetokenurl;
    }

    public void setBasetokenurl(String basetokenurl) {
        this.basetokenurl = basetokenurl;
    }

    public String getGranttype() {
        return granttype;
    }

    public void setGranttype(String granttype) {
        this.granttype = granttype;
    }

    public String getTicketurl() {
        return ticketurl;
    }

    public void setTicketurl(String ticketurl) {
        this.ticketurl = ticketurl;
    }

    public String getPay_unifiedorder() {
        return pay_unifiedorder;
    }

    public void setPay_unifiedorder(String pay_unifiedorder) {
        this.pay_unifiedorder = pay_unifiedorder;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
