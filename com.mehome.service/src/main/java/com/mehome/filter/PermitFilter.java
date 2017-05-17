package com.mehome.filter;

import com.mehome.config.SpringContextHelper;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.utils.Permits;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/5/12.
 */
public class PermitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Enumeration<String> attrNames = session.getAttributeNames();
        AdministratorBean loginUser = null;
        while (attrNames.hasMoreElements()) {
            if ("user".equals(attrNames.nextElement())) {
                loginUser = (AdministratorBean) session.getAttribute("user");
                break;
            }
        }
        Permits permits = SpringContextHelper.getPermits((request.getRequestURI().replace("/", "")));
        if (null != permits) {
            if (permits.needLogin()) {
                if (null != loginUser) {//已登录
                    //判断该用户是否有权限
                    RoleEnum[] roleEnums = permits.role();
                    boolean hasRole = false;
                    for (RoleEnum item : roleEnums
                            ) {
                        if (item.getRole().equals(loginUser.getRole())) {
                            hasRole = true;
                            break;
                        }
                    }
                    //根据是否有权限来进行跳转
                    if (hasRole) {
                        chain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect("/html/page/403.html");
                    }
                } else {//未登录，去登录
                    response.sendRedirect("/html/login.html");
                }
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
