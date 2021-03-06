package com.mehome.config;

import com.mehome.filter.LogFilter;
import com.mehome.filter.PermitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBeanRole() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("RolefilterRegistration");

        PermitFilter permitFilter = new PermitFilter();
        registrationBean.setFilter(permitFilter);
        List<String> list = new ArrayList<String>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        registrationBean.setOrder(1);


        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBeanLog() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("LogfilterRegistration");

        LogFilter permitFilter = new LogFilter();
        registrationBean.setFilter(permitFilter);
        List<String> list = new ArrayList<String>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        registrationBean.setOrder(2);


        return registrationBean;
    }
}
