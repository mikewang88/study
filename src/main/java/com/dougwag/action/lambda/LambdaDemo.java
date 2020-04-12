package com.dougwag.action.lambda;

import java.util.function.Consumer;

/**
 * @Author: MikeWang
 * @Date: 2020/2/25 10:53 PM
 * @Description:
 */
public class LambdaDemo {
    public static void main(String[] args) {
        Consumer<String> consumer=(s)->System.out.println(s);
        consumer.accept("Lambda demo");

    }
}
