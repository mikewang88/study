package com.aboutspring.designmode.proxy;

/**
 * @Author: MikeWang
 * @Date: 2020/3/28 1:48 PM
 * @Description:
 */
public class TeacherCang2 implements Girl {
    @Override
    public boolean dating(float length) {
        System.out.println("都可以的");
        return true;
    }
}
