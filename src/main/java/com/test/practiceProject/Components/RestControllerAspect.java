package com.test.practiceProject.Components;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 9/24/2024
 * @Time 4:37 PM
 */
@Aspect
@Component
public class RestControllerAspect {
//    private final Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(public * com.test.practiceProject.Controller.*.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        logger.info(":::::AOP Before REST call:::::" + pjp);
    }

    @AfterReturning(pointcut = "execution(public * com.test.practiceProject.Controller.*.*(..))", returning = "returnValue")
    public void logAfterRESTCall(JoinPoint pjp, Object returnValue) throws Throwable {
        logger.info(":::::AOP After REST call:::::" + pjp);
        logger.info(":::::AOP After REST call Return Value:::::" + returnValue);
    }
}
