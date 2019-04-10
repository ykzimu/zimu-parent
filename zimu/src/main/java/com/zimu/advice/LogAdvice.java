package com.zimu.advice;

import com.zimu.common.utils.HttpServletManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAdvice {


    @Pointcut("execution( * com.zimu.controller..*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {

    }


    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = HttpServletManager.getRequest();
        log.info("RequestURI：{}\tMethod：{}", request.getRequestURI(), request.getMethod());
        log.info("ParameterMap：{}", request.getParameterMap());
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        //log.info("AfterReturning");
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("处理耗时：{}", endTime - startTime);
        return obj;
    }
}
