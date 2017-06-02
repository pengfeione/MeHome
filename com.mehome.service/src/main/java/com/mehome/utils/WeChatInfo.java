package com.mehome.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.WeChatUserInfo;
import com.mehome.exceptions.ArgumentException;
import com.mehome.exceptions.InfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

/**
 * Created by ${pengfei} on 2016/3/24 0024.
 */
@Component
public class WeChatInfo {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public WeChatProperties weChatProperties;
    RestTemplate restTemplate = new RestTemplate();

    /**
     * 生成用于获取access_token的Code的Url
     *
     * @param redirectUrl
     * @return
     */
    public String getRequestCodeUrl(String redirectUrl) {

        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                weChatProperties.getAppid(), redirectUrl, "snsapi_userinfo", "STATE");
    }

    /*
    * 第二步：通过code换取网页授权access_token
    * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    */
    public String GetWeiXinToken(String code) {

        String tokenurl = weChatProperties.getTokenurl() +
                "?appid=" + weChatProperties.getAppid()
                + "&secret=" + weChatProperties.getSecret()
                + "&code=" + code
                + "&grant_type=" + weChatProperties.getGrant_type();

        String requstData = restTemplate.getForObject(tokenurl, String.class);
        return requstData;
    }

    //第三步：拉取用户信息(需scope为 snsapi_userinfo)
    //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    public WeChatUserInfo GetWeixinInfo(String code) {
        String requstDatam = GetWeiXinToken(code);
        WeChatAccessToken accessToken = JSON.parseObject(requstDatam, WeChatAccessToken.class);
        if (accessToken.access_token == null) {
            throw new InfoException("微信拉取用户数据异常");
        }
        String userinfoUrl = weChatProperties.getUserinfourl() +
                "?access_token=" + accessToken.getAccess_token()
                + "&openid=" + accessToken.getOpenid()
                + "&lang=zh_CN";
        //返回用户给到 h5
        return JSONObject.parseObject(restTemplate.getForObject(userinfoUrl, String.class), WeChatUserInfo.class);
    }
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");

}
