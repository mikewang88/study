package com.dougwag.action.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: MikeWang
 * @Date: 2020/3/3 4:15 PM
 * @Description:
 */
public class UnsafeDemo {
    public static void main(String[] args) {

    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }
}
