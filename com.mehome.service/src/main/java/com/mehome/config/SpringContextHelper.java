package com.mehome.config;

import com.alibaba.fastjson.util.TypeUtils;
import com.mehome.utils.Permits;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;
    private static Map<String, Object> requestMappingMap = new HashMap<String, Object>();
    private static Map<String, Permits> permitsMap = new HashMap<String, Permits>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        requestMappingMap = applicationContext.getBeansWithAnnotation(org.springframework.web.bind.annotation.RequestMapping.class);
        Class<? extends Object> clazz = null;
        String base = "";
        for (Map.Entry<String, Object> entry : requestMappingMap.entrySet()) {
            clazz = entry.getValue().getClass();//获取到实例对象的class信息
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            if (null != requestMapping) {
                String[] paths = requestMapping.value();
                if (paths.length > 0) {
                    base = paths[0];
                }
            }
            Method[] methods = clazz.getDeclaredMethods();
            String methodPath = "";
            for (int i = 0; i < methods.length; i++) {
                Permits permits = methods[i].getAnnotation(Permits.class);
                PostMapping postMapping = methods[i].getAnnotation(PostMapping.class);
                if (null != postMapping) {
                    String[] paths = postMapping.value();
                    if (paths.length > 0) {
                        methodPath = paths[0];

                    }
                }
                if (null != permits) {
                    permitsMap.put(base + methodPath, permits);
                }
            }
        }
    }

    /**
     * 获取spring中加载的bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return TypeUtils.castToJavaBean(context.getBean(name), clazz);
    }

    /**
     * 通过requestUri获取权限
     *
     * @param requestUri
     * @return
     */
    public Permits getPermits(String requestUri) {
        return permitsMap.get(requestUri);
    }
}
