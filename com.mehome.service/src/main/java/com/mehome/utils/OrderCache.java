package com.mehome.utils;

import net.sf.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by trancy on 2017/7/21.
 */
public class OrderCache {
    //    var param = '{"orderId":' + orderId + ',"tradeType":"JSAPI","payer":' + uid + ',"payType":"wechat"}';
    public static Map<String, JSONObject> orderCache = new ConcurrentHashMap<String, JSONObject>();



}
