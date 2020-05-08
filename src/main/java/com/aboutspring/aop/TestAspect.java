package com.aboutspring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 7:20 PM
 * @Description:
 */
@Aspect
@Component
public class TestAspect {

    // 定义切点（切入位置）
    @Pointcut("execution(* com.aboutspring.aop.TestBean.*(..))")
    private void pointcut(){}


    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("我是前置通知");
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("我是后置通知");
    }

}
