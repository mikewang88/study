package com.dougwag.action.base;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: MikeWang
 * @Date: 2020/4/2 9:34 PM
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int[] num1=new int[10];//定义一个长度10的数组（10个人）
        int[] num2=new int[100];//这个数组用来存储数据（总共1000人）
        //先给数组2赋值，不然空数组会导致后面取随机数重复
        for(int i=0;i<num2.length;i++){
            //这里＋1是因为取随机数的范围是从零开始的，为了避免数组出现零，所以＋1
            num2[i]=i+1;
        }
        Random r =new Random();//生成随机数的类
        int index=-1;//定义一个变量记录下标
        //创建10个不重复的随机数（核心代码块）
        for(int i=0;i<num1.length;i++){
            index=r.nextInt(num2.length-i);
            num1[i]=num2[index];
            // 下面代码是用来把选择的数调换到数组的最后一位，避免重复
            int b=num2[index];
            num2[index]=num2[num2.length-1-i];
            num2[num2.length-1-i]=b;
        }
        //排序数组并输出
        Arrays.sort(num1);
        System.out.println("以下10个人，可以获得得到购买车牌的资格。"+Arrays.toString(num1));
    }
}
