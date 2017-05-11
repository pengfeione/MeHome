package com.mehome.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.HttpUtil;
import com.mehome.exceptions.JsonConvertFailedException;
import org.apache.log4j.Logger;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeChatTokenService {
    @Autowired
    public WeChatProperties weChatProperties;
    public  RestTemplate template;
    private static Logger log = Logger.getLogger(WeChatTokenService.class);

    private static ConcurrentHashMap<String, Object> tokenCache = new ConcurrentHashMap<String, Object>();
    private static ConcurrentHashMap<String, Object> ticketCache = new ConcurrentHashMap<String, Object>();

    private String access_token;

    public String getToken() {
        try {
            String tokenJson = null;
            Long currentTimeStamp = new Date().getTime();
            // 保存2小时
            if (tokenCache.containsKey("tokenTime") && (currentTimeStamp - (Long) tokenCache.get("tokenTime")) < 1000 * 60 * 60) {
                tokenJson = (String) tokenCache.get("tokenJson");
            } else {
                Map<String, Object> ret = new HashMap<String, Object>();
                ret.put("grant_type", weChatProperties.getGranttype());
                ret.put("appid", weChatProperties.getAppid());
                ret.put("secret",weChatProperties.getSecret());

                JSONObject jsonObject=template.getForObject(weChatProperties.getBasetokenurl(),JSONObject.class);
                if (jsonObject != null) {
                    setAccess_token(jsonObject.get("access_token").toString());
                    tokenJson = JSON.toJSONString(jsonObject);
                } else {
                    return "token return null";
                }
                tokenCache.put("tokenTime", currentTimeStamp);
                tokenCache.put("tokenJson", tokenJson);
            }
            return tokenJson;
        } catch (Exception e) {
            log.error("GetWeiXinTokenServlet failed, caused by:" + e);
        }
        return null;
    }
    public String getTicket() {
        try {
            String ticketJson = null;
            Long currentTimeStamp = new Date().getTime();
            // 保存2小时
            if (ticketCache.containsKey("ticketTime") && (currentTimeStamp - (Long) ticketCache.get("ticketTime")) < 1000 * 60 * 60 && checkTicketCacheTicket((String) ticketCache.get("ticketJson"))) {
                ticketJson = (String) ticketCache.get("ticketJson");
                log.info("get ticket cache: " + ticketJson);
            } else {
                getToken();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("access_token", getAccess_token());
                params.put("type","jsapi");
                RestTemplate restTemplate = new RestTemplate();
                String resultStr= restTemplate.getForObject(weChatProperties.getTicketurl(), String.class,params);
                if (resultStr != null) {
                    ticketJson = resultStr;
                } else {
                    return "ticket return null";
                }
                ticketCache.put("ticketTime", currentTimeStamp);
                ticketCache.put("ticketJson", ticketJson);
            }
            return ticketJson;
        } catch (Exception e) {
            log.error("GetWeiXinTicketServlet failed, caused by:" + e);
        }
        return null;
    }
    @SuppressWarnings({"unchecked"})
    public Map<String, Object> getSignPackage(String url) {
        Map<String, Object> ret = new HashMap<String, Object>();
        //获取Ticket
        String ticketStr = getTicket();
        String ticket = null;
        //得到nonceStr
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        try {
            Map<String, Object> ticketMap = JSON.parseObject(ticketStr, Map.class);
            ticket = ticketMap.get("ticket").toString();

            //注意这里参数名必须全部小写，且必须有序
            string1 = "jsapi_ticket=" + ticket +
                    "&noncestr=" + nonce_str +
                    "&timestamp=" + timestamp +
                    "&url=" + url;

            //得到signature
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            //封装返回结果

            ret.put("url", url);
            ret.put("appid", weChatProperties.getAppid());
            ret.put("ticket", ticket);
            ret.put("noncestr", nonce_str);
            ret.put("timestamp", timestamp);
            ret.put("signature", signature);
            return ret;
        }catch (NoSuchAlgorithmException e) {
            log.error(" getSignPackage Method Is exception NoSuchAlgorithmException, cause: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error(" getSignPackage Method Is exception UnsupportedEncodingException, cause: " + e.getMessage());
        }
        return null;
    }
    //******************************************************************
    //************************** tool util
    //******************************************************************
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @SuppressWarnings("unchecked")
    public boolean checkTicketCacheTicket(String jsonStr) throws JsonConvertFailedException {
        Map<String, Object> ticketMap;
        ticketMap = JSON.parseObject(jsonStr, Map.class);
        if (ticketMap.get("ticket") != null) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        WeChatProperties wechat = new WeChatProperties();
//        System.out.println(wechat.getSignPackage("http://share.vlong.tv"));
//    }

}
