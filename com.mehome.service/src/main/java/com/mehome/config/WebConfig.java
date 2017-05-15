package com.mehome.config;

import com.mehome.filter.LogFilter;
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
    public FilterRegistrationBean helloFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("hello");

        LogFilter helloFilter = new LogFilter();
        registrationBean.setFilter(helloFilter);
        List<String> list = new ArrayList<String>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}