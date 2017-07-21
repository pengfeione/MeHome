package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;

public class SignUtils {

    public static String sign(JSONObject jsonObject, String key) {
        Set<String> keySet = jsonObject.keySet();
        String[] param = new String[keySet.size()];
        int index = 0;
        for (String itemKey : keySet) {
            if (jsonObject.getString(itemKey).trim().length() > 0) {
                param[index++] = (itemKey + "=" + jsonObject.getString(itemKey));
            }
        }
        Arrays.sort(param);
        StringBuffer strBuffer = new StringBuffer();
        for (String str : param) {
            strBuffer.append(str + "&");
        }
        strBuffer.append("key=").append(key);
        System.out.println(strBuffer.toString());
        return md5(strBuffer.toString()).toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param string
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
