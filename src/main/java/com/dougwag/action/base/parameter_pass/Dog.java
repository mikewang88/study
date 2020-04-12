package com.dougwag.action.base.parameter_pass;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/6 4:40 PM
 * @Description:
 */
public class Dog {
    String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress() {
        return super.toString();
    }
}
