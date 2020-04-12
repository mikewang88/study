package com.dougwag.action.jit;

import java.util.Random;

/**
 * @Author: MikeWang
 * @Date: 2020/2/27 4:57 PM
 * @Description:
 */
// 参数：-XX:+PrintCompilation -XX:-TieredCompilation（关闭分层编译）
public class JITDemo2 {
//    private static Random random = new Random();
//
//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        int count = 0;
//        int i = 0;
//        while (i++ < 15000){
//            //System.out.println(i);
//            count += plus();
//        }
//        System.out.println("time cost : " + (System.currentTimeMillis() - start));
//    }
//
//    // 调用时，编译器计数器+1
//    private static int plus() {
//        return random.nextInt(10);
//    }
//}

    private static Random random = new Random();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        plus();
        System.out.println("time cost : " + (System.currentTimeMillis() - start));
    }

    // 调用时，编译器计数器+1
    private static int plus() {
        int count = 0;
        // 每次循环，编译器计数器+1
        for (int i = 0; i < 15000; i++) {
            //System.out.println(i);
            count += random.nextInt(10);
        }
        return random.nextInt(10);
    }
}
