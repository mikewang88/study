package com.aboutspring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 6:54 PM
 * @Description:
 */
public class AOPMainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPAppConfig.class);
//        BeanQ bq = context.getBean(BeanQ.class);
//        bq.do1("task1", 20);
//        System.out.println();
//
//        bq.service1("service1");
//
//        System.out.println();
//        bq.service2("ssss");

        TestBean testBean = context.getBean(TestBean.class);
        testBean.hello();
        TestBean testBean2 = context.getBean(TestBean.class);
        if (testBean == testBean2){
            System.out.println("1");
        }
    }
}
