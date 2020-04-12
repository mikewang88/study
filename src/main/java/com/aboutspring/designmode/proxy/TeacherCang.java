package com.aboutspring.designmode.proxy;

/**
 * @Author: MikeWang
 * @Date: 2020/3/28 1:43 PM
 * @Description:
 */
public class TeacherCang implements Girl{
    public boolean dating(float length) {
        if (length >= 1.7F) {
            System.out.println("身高可以，可以约！");
            return true;
        }
        System.out.println("身高不可以，不可约！");
        return false;
    }
}
