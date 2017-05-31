package com.mehome.filter;

import com.mehome.exceptions.InfoException;
import com.mehome.utils.APIBaseResult;
import com.mehome.utils.BodyRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.util.NestedServletException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;


/**
 * Created by {renhui} on 2016-10-17.
 */
//@WebFilter(filterName = "baseFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final Set<String> allowDomain = new HashSet<String>();

    static {
        allowDomain.add("http://m.mjiahome.com");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println(httpServletRequest.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //允许部分跨域
        Map<String, String> headerMap = new HashMap<String, String>();
        Enumeration<String> enumeration = httpServletRequest.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            try {
                headerMap.put(enumeration.nextElement(), httpServletRequest.getHeader(enumeration.nextElement()));
            } catch (Exception e) {
            }
        }
//        if (allowDomain.contains(headerMap.get("accept"))) {
        response.setHeader("Access-Control-Allow-Origin", "http://m.mjiahome.com");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,content-type,Accept");
//        }
        BodyRequestWrapper bodyRequestWrapper = new BodyRequestWrapper(httpServletRequest);
        log.info("\n------------------------------------------------------------------------------------------" +
                "\n\t     user-agent : " + headerMap.get("accept") +
                "\n\t      client IP : " + httpServletRequest.getRemoteAddr() +
                "\n\t   content type : " + httpServletRequest.getContentType() +
                "\n\t    request url : " + httpServletRequest.getRequestURL() +
                "\n\t      host name : " + InetAddress.getLocalHost().getHostName() +
                "\n\t     parameters : " + buildGetParameters(httpServletRequest) +
                "\n\t           body : " + bodyRequestWrapper.getBody() +
                "\n------------------------------------------------------------------------------------------");
        String type = httpServletRequest.getParameter("type");
        String st = httpServletRequest.getRequestURI().toString();
        boolean isMobile = httpServletRequest.getHeader("user-agent").indexOf("Mobile") > 0;
        if (st.endsWith("/share") && !isMobile) {
            //是手机请求的
        }
        printHeader(httpServletRequest);
        try {
            filterChain.doFilter(bodyRequestWrapper, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String buildGetParameters(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> parameterName = httpServletRequest.getParameterNames();
        while (parameterName.hasMoreElements()) {
            String name = parameterName.nextElement();
            stringBuilder.append("&" + name + "=" + httpServletRequest.getParameter(name));
        }
        return stringBuilder.toString();
    }

    public void printHeader(HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> names = httpServletRequest.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String header = httpServletRequest.getHeader(name);
            stringBuilder.append("\n" + name + " : " + header);
        }
        log.info(stringBuilder.toString());
    }

    @Override
    public void destroy() {

    }
}
