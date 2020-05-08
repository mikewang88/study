package com.aboutspring.aop;

import org.springframework.stereotype.Component;

/**
 * @Author: MikeWang
 * @Date: 2020/4/28 7:20 PM
 * @Description:
 */
@Component
public class TestBean {

    public void hello() {
        System.out.println("hello");
    }
}
