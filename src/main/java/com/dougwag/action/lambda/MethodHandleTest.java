package com.dougwag.action.lambda;


import com.alibaba.nacos.client.naming.utils.RandomUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;


/**
 * @Author: MikeWang
 * @Date: 2020/2/25 6:23 PM
 * @Description:
 */
public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println("hello i am classA =="+s);
        }
    }

    static class ClassB{
        public void println(String s){
            System.out.println("hello i am classB =="+s);
        }
    }

    private static MethodHandle gtPringLmMH(Object obj) throws Throwable{
//        MethodHandle
//        MethodHandle api要比反射快很多因为访问检查在创建的时候就已经完成了，而不是像反射一样等到运行时候才检查。但同时，Method Handles比反射更难用，因为没有列举类中成员，获取属性访问标志之类的机制

//        它是对可直接执行的方法的类型的引用，或者说，它是一个有能力安全调用方法的对象。
//        通过句柄我们可以直接调用该句柄所引用的底层方法。从作用上来看，方法句柄类似于反射中的Method类，但是方法句柄的功能更加强大、使用更加灵活、性能也更好。
        // 方法句柄
        MethodType mt = MethodType.methodType(void.class,String.class);
        return MethodHandles.lookup().findVirtual(obj.getClass(),"println",mt).bindTo(obj);
    }

    public static void main(String[] args) throws Throwable {
        Object obj = RandomUtils.nextInt(100) % 2 ==0? new ClassA()
                : new ClassB();
        gtPringLmMH(obj).invokeExact("MethodHandle");
    }
}
