package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;
    private static Map<String, Object> requestMappingMap = new HashMap<String, Object>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        requestMappingMap = applicationContext.getBeansWithAnnotation(org.springframework.web.bind.annotation.RequestMapping.class);
        Class<? extends Object> clazz = null;
        for (Map.Entry<String, Object> entry : requestMappingMap.entrySet()) {
            clazz = entry.getValue().getClass();//获取到实例对象的class信息
            System.out.println(clazz.getSimpleName());
            Class<? extends Object>[] interfaces = clazz.getInterfaces();
            for (Class<? extends Object> aInterface : interfaces) {
                //接口信息
            }
        }

    }

    public <T> T getBean(String name, Class<T> clazz) {
        return TypeUtils.castToJavaBean(context.getBean(name), clazz);
    }
}
