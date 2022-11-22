package com.centime.assessment.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Aspect
@Component
public class CustomLogger {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    @Around("execution(* *(..)) && @annotation(LogMethodParam)")
    public Object logMethod(ProceedingJoinPoint jp) throws Throwable {
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Object result = jp.proceed(values);
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }
        logger.info("Method " + jp.getSignature().getName() + " Invoked");
        if (!params.isEmpty())
            logger.info("Received Params: " + params);
        return result;
    }
}