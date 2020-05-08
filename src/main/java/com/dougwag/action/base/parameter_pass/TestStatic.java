package com.dougwag.action.base.parameter_pass;

/**
 * @Author: MikeWang
 * @Date: 2020/5/7 5:08 PM
 * @Description:
 */
public class TestStatic {

    static class A {
        public void scan(){
            doScan();
        }
        protected void doScan(){
            System.out.println("A.doScan");
        }
    }
    static class B extends A {
        @Override
        protected void doScan() {
            System.out.println("B.doScan");
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.scan();  //我的输出结果是什么？
    }
}

