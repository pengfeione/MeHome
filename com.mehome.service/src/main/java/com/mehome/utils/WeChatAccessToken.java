package com.mehome.utils;

/**
 * Created by pengfei on 2017/5/10.
 */
public class WeChatAccessToken {
    public String openid;
    public String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public String getAccess_token() {
        return access_token;
    }

    public String getOpenid() {
        return openid;
    }
}
