package com.jvm.zzmAction;

import sun.misc.Unsafe;
import sun.misc.VM;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @Author: MikeWang
 * @Date: 2020/2/11 2:38 PM
 * @Description:
 */
public class DirectoryMemoryOomError {
    private static int ONE_MB = 1024 * 1024;
    private static int index = 0;

    public static void main(String[] args) {
        try {
            System.out.println(VM.maxDirectMemory() / ONE_MB);
            ByteBuffer.allocateDirect(ONE_MB * 100);

            // 直接内存操作
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            while (true) {
                index++;
                // 分配内存
                long l = unsafe.allocateMemory(ONE_MB);
                // 释放内存
                unsafe.freeMemory(l);
            }
        } catch (Exception | Error e) {
            System.out.println("index:" + index);
            e.printStackTrace();
        }
    }
}
