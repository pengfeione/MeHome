package com.mehome.filter;

import com.mehome.utils.BodyRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;


/**
 * Created by {renhui} on 2016-10-17.
 */
@WebFilter(filterName = "baseFilter", urlPatterns = "/*")
public class BaseFilter implements Filter {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final Set<String> allowDomain = new HashSet<String>();
    static{
        allowDomain.add("http://test.youxiduo.com");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //允许部分跨域
        Map<String,String> headerMap = new HashMap<String,String>();
        try{
            Enumeration<String> enumeration = httpServletRequest.getHeaderNames();
            while(enumeration.hasMoreElements()){
                log.info(enumeration.nextElement()+" : "+httpServletRequest.getHeader(enumeration.nextElement()));
                headerMap.put(enumeration.nextElement(),httpServletRequest.getHeader(enumeration.nextElement()));
            }
            if(allowDomain.contains(headerMap.get("accept"))){
                response.setHeader("Access-Control-Allow-Origin", headerMap.get("accept"));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            }
        }catch (Exception e){
           log.info("跨域请求失败",e.getMessage());
        }
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

        }
        printHeader(httpServletRequest);
        filterChain.doFilter(bodyRequestWrapper, response);
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
    public void printHeader(HttpServletRequest httpServletRequest){
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> names = httpServletRequest.getHeaderNames();
        while(names.hasMoreElements()){
           String name =  names.nextElement();
           String header = httpServletRequest.getHeader(name);
           stringBuilder.append(name+" : "+header).append("\n");
        }
        log.info(stringBuilder.toString());
    };
    @Override
    public void destroy() {

    }
}
