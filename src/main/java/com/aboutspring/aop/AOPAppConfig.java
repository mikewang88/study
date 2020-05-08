package com.aboutspring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 6:53 PM
 * @Description:
 */
@Configuration
@ComponentScan("com.aboutspring.aop")
@EnableAspectJAutoProxy
public class AOPAppConfig {
}
