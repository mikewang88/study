package com.jvm.jit;

/**
 * @Author: MikeWang
 * @Date: 2020/5/29 10:34 AM
 * @Description:
 * -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 *
 * 通过设置 JVM 参数来减小热点阈值或增加方法体阈值，以便更多的方法可以进行内联，但这种方法意味着需要占用更多地内存；
在编程中，避免在一个方法中写大量代码，习惯使用小方法体；
尽量使用 final、private、static 关键字修饰方法，编码方法因为继承，会需要额外的类型检查。
 */
public class JIT_inline_test {
    public static void main(String[] args) {
        for(int i=0; i<1000000; i++) {// 方法调用计数器的默认阈值在 C1 模式下是 1500 次，在 C2 模式在是 10000 次，我们循环遍历超过需要阈值
            add1(1,2,3,4);
        }
    }

    private static int add1(int x1, int x2, int x3, int x4) {
        return add2(x1, x2) + add2(x3, x4);
    }
    private static int add2(int x1, int x2) {
        return x1 + x2;
    }
}
