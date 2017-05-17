package com.mehome.config;

import com.alibaba.fastjson.util.TypeUtils;
import com.mehome.utils.Permits;
import com.mehome.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
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
            for (int i = 0; i < methods.length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                Annotation[] annotations = methods[i].getAnnotations();
                for (Annotation ann : annotations
                        ) {
                    stringBuilder.append("\n\t" + ann.annotationType().getSimpleName());
                }
                System.out.println(clazz.getSimpleName()+"--"+methods[i].getName() + stringBuilder);
                String methodPath = "";
                Permits permits = methods[i].getAnnotation(Permits.class);
                if (null == permits) {
                    continue;
                }
                GetMapping methodGetMapping = methods[i].getAnnotation(GetMapping.class);
                if (null != methodGetMapping) {
                    methodPath = hasMethodPath(methodGetMapping.value(), methodGetMapping.path());
                    if (StringUtils.isNotNull(methodPath)) {
                        permitsMap.put((base + methodPath).replace("/", ""), permits);
                        continue;
                    }
                }
                PostMapping methodPostMapping = methods[i].getAnnotation(PostMapping.class);
                if (null != methodPostMapping) {
                    methodPath = hasMethodPath(methodPostMapping.value(), methodPostMapping.path());
                    if (StringUtils.isNotNull(methodPath)) {
                        permitsMap.put((base + methodPath).replace("/", ""), permits);
                        continue;
                    }
                }
                RequestMapping methodRequestMapping = methods[i].getAnnotation(RequestMapping.class);
                if (null != methodRequestMapping) {
                    methodPath = hasMethodPath(methodRequestMapping.value(), methodRequestMapping.path());
                    if (StringUtils.isNotNull(methodPath)) {
                        permitsMap.put((base + methodPath).replace("/", ""), permits);
                    }
                }
            }
        }
    }

    private String hasMethodPath(String[] values, String[] paths) {
        String methodPath = "";
        if (values.length > 0) {
            methodPath = values[0];
        } else {
            if (paths.length > 0) {
                methodPath = paths[0];
            }
        }
        return methodPath;
    }


    /**
     * 通过requestUri获取权限
     *
     * @param requestUri
     * @return
     */
    public static Permits getPermits(String requestUri) {
        return permitsMap.get(requestUri);
    }
}
