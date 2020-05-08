package com.aboutspring.autowiredDesign;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 10:27 AM
 * @Description:
 */
public class MainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.query();
    }
}
