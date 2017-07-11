package com.mehome.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by {renhui} on 2016-10-18.
 */
public class OrderIdUtils {
    private static final char[] NUMBER_CHAR = "1234567890".toCharArray();
    public static final char[] FULL_ALPHABET_WITH_NUMBER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static synchronized String getOrderId(String userId) {
        String payIdWithoutTail = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL%2$s", new Date(), StringUtils.getRandomCode(6, NUMBER_CHAR));
        return payIdWithoutTail + calcPayIdTail(payIdWithoutTail + getLocalMac() + userId);
    }
    
    public static synchronized String getOrderId(){
    	String payIdWithoutTail = String.format("%1$tY%1$tm%1$td", new Date());
    	return payIdWithoutTail;
    }

    private static char calcPayIdTail(String in) {
        char[] charList = in.toCharArray();
        while (charList.length > 1) {
            long sum = 0;
            for (int i = 0; i < charList.length; i++) {
                sum += charList[i] - '0';
            }
            charList = String.valueOf(sum).toCharArray();
        }
        return charList[0];
    }

    public static String getAutoId(){
    	 String payIdWithoutTail = StringUtils.getRandomCode(11, FULL_ALPHABET_WITH_NUMBER);
         return payIdWithoutTail + calcPayIdTail(payIdWithoutTail + getLocalMac());
    }
    
    private static String getLocalMac() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            //获取网卡，获取地址
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                //字节转换为整数
                int temp = mac[i] & 0xff;
                sb.append(temp);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }
    public static void main(String[] args) {
//    	String orderId=OrderIdUtils.getOrderId("100000000000000000");
//    	int length=orderId.length();
//    	System.out.println(length);
    	
    	
//    	String newOrderId=OrderIdUtils.getOrderId();
//    	System.out.println(newOrderId);
//    	long max=0L;
//    	String id=String.format("%06d", max);
    	System.out.println(getAutoId());
	}
}
