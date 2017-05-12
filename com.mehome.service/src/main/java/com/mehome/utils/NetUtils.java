package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2016-12-28.
 */
public class NetUtils {
    protected static final Logger log = LoggerFactory.getLogger("NetUtils");

    /**
     * 判断ip地址是否是本机地址
     *
     * @param ipV4 ip地址
     * @return
     */
    public static boolean isLocal(String ipV4) {
        if ("127.0.0.1".equals(ipV4)) {
            return true;
        }
        boolean access = false;
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces
                    .hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.getHostAddress().indexOf(".") != -1) {
                        String machineIp = ip.getHostAddress();
                        if (ipV4.equals(machineIp)) {
                            access = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("checkMachineIPAccessForTask failed, caused by: " + e.getMessage());
            access = false;
        }
        return access;
    }

    /**
     * 判断机器的IP地址是否在白名单中
     * 如果白名单不设置值，表示都不通过
     *
     * @return
     */
    public static boolean checkMachineIPAccessForTask(String ips) {
        boolean access = false;
        try {
            List<String> ipWhiteList = Arrays.asList(ips.split(","));
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces
                    .hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.getHostAddress().indexOf(".") != -1) {
                        String machineIp = ip.getHostAddress();
                        if (ipWhiteList.contains(machineIp)) {
                            access = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("checkMachineIPAccessForTask failed, caused by: " + e.getMessage());
            access = false;
        }
        return access;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getHeader(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> names = httpServletRequest.getHeaderNames();
        while (names.hasMoreElements()) {
            try {
                String name = names.nextElement();
                String header = httpServletRequest.getHeader(name);
                jsonObject.put(name, header);
            } catch (Exception e) {
            }
        }
        return jsonObject.toJSONString();
    }
}
