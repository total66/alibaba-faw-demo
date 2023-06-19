package com.ebanma.cloud.myannotation.config;

import com.ebanma.cloud.myannotation.common.AvoidRepeatableCommit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 于秦涛
 * @version $ Id: AvoidRepeatableCommitAspectJ, v 0.1 2023/06/19 11:18 98077 Exp $
 */
@Component
@Aspect
@Slf4j
public class AvoidRepeatableCommitAspectJ {

    @Pointcut("@annotation(com.ebanma.cloud.myannotation.common.AvoidRepeatableCommit)")
    public void cutPoint() {

    }

    @Around("cutPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        //获取方法参数
        log.info("参数为{}", point.getArgs()[0]);

        //得到注解中的值
        AvoidRepeatableCommit avoidRepeatableCommit = method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        if (timeout < 0) {
            timeout = 5;
        }

        System.out.println(className);
        System.out.println(methodName);
        System.out.println(timeout);

        //继续进行程序
        return point.proceed();
    }

}

