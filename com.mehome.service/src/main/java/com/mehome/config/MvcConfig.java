package com.mehome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/company.html").setViewName("index");
        registry.addViewController("/lease.html").setViewName("lease");
        registry.addViewController("/product.html").setViewName("product");
        registry.addViewController("/user.html").setViewName("user");
        registry.addViewController("/cooperation.html").setViewName("cooperation");
    }
}
