package com.mehome.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 高德地图web api工具类
 * @author xuyixun21
 *
 */
public class LbsAmapUtils {
	private static String mapUrl="http://restapi.amap.com/v3/place/text";
	private static String mapKey="2e174be6175a21db94251aa6c2542d90";
	
	/**
	 * 输入中文地址返回经纬度数组   数组第一个元素为经度   第二个元素为维度
	 * @param address
	 * @return
	 */
	public static String[] getPosition(String address){
		String[] locationArray=null;
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("key", mapKey);
		params.put("keywords", address);
		String retmessage;
		try {
			retmessage = HttpUtils.get(mapUrl, params);
			JSONObject mapObject=JSONObject.parseObject(retmessage);
			JSONArray pois=mapObject.getJSONArray("pois");
			JSONObject poisObj=pois.getJSONObject(0);
			String location=(poisObj==null?null:poisObj.getString("location"));
			if(!StringUtils.isEmpty(location)){
				locationArray=location.split(",");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locationArray;
	}
	/**
	 * 格式化
	 * @param address
	 * @return
	 */
	public static String formatPosition(String address){
		String[] location=getPosition(address);
		if(location!=null&&location.length>1){
			return location[0]+","+location[1];
		}
		return null;
	}
	public static void main(String[] args) {
		String[] location=getPosition("三林路1515号");
		for (String string : location) {
			System.out.println(string);
		}
	}
}
