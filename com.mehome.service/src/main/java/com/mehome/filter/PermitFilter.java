package com.mehome.filter;

import com.mehome.config.SpringContextHelper;
import com.mehome.utils.Permits;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI());
        Permits permits = SpringContextHelper.getPermits((request.getRequestURI().replace("/", "")));
        if (null != permits) {
            System.out.println(permits);
        }
        chain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
