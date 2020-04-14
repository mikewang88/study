package com.dougwang.algorithm_and_datastructure.ds05;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:22 PM
 * @Description:
 * 静态数据求中位数
 */
public class Heap02 {

    public static void main(String[] args) {
        List<Integer> total = new ArrayList<Integer>();
        total.add(4);
        total.add(2);
        total.add(3);
        total.add(1);
        total.add(5);
        total.add(6);
        double a = median(total);
        System.out.println(a);
    }

    private static double median(List<Integer> total) {
        double j = 0;
        //集合排序
        Collections.sort(total);
        int size = total.size();
        if(size % 2 == 1){
            j = total.get((size-1)/2);
        }else {
            //加0.0是为了把int转成double类型，否则除以2会算错
            j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
        }
        return j;
    }
}
