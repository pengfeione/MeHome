package com.mehome.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.mehome.exceptions.InfoException;
import com.mehome.utils.Permits;
import com.mehome.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by renhui on 2017/5/14.
 */
@Aspect
@Component
public class ApiLogAop {
    private static final Logger logger = LoggerFactory.getLogger("");
    private static boolean isJsonFormat = true;
    @Value("${cros}")
    private String cros;

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.mehome.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint pjp) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n--------------------------------------------");
        long beginTime = System.currentTimeMillis();
        Object target = pjp.getTarget();
        Class<? extends Object> clazz = target.getClass();
        String base = "";
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        if (null != requestMapping) {
            String[] paths = requestMapping.value();
            if (paths.length > 0) {
                base = paths[0];
            }
        }
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodPath = "";
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (null != postMapping) {
            String[] paths = postMapping.value();
            if (paths.length > 0) {
                methodPath = paths[0];

            }
        }
        stringBuilder.append("\nrequest path : " + base + methodPath);
        //构造params
        ParameterNameDiscoverer parameterNameDiscoverer =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        Object[] args = pjp.getArgs();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            String parameter = paramNames[i];
            if (arg instanceof HttpServletRequest) {
            } else if (arg instanceof HttpServletResponse) {
            } else if (arg instanceof Map<?, ?>) {
            } else {
                jsonObject.put(parameter, JSONObject.parseObject(JSONObject.toJSONString(arg)));
            }
        }
        stringBuilder.append("\n\t  params : " + jsonObject);
        String errMsg = "";
        Result result = null;
        ResponseEntity<Result> resultEntity = null;
        try {
            resultEntity = TypeUtils.castToJavaBean(pjp.proceed(), ResponseEntity.class);
            result = resultEntity.getBody();
            stringBuilder.append("\nstatus code : " + resultEntity.getStatusCodeValue());
            stringBuilder.append("\n\t\tresult : " + result);
            stringBuilder.append("\n cost time : " + (System.currentTimeMillis() - beginTime) + "ms");
            stringBuilder.append("\n--------------------------------------------");
            logger.info(stringBuilder.toString());
            return resultEntity;
        } catch (Throwable e) {
            if (e instanceof InfoException) {
                errMsg = e.getMessage();
            } else {
                errMsg = "系统异常！";
            }
            stringBuilder.append("\n exception : " + e.getMessage());
            stringBuilder.append("\n--------------------------------------------");
            logger.info(stringBuilder.toString());
            return ResponseEntity
                    .ok()
                    .header("Access-Control-Allow-Origin", cros)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(Result.buildError(errMsg));

        }
    }
}
