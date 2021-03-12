package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/8 6:30 PM
 * @Description:  x 的平方根
 */
public class L69 {
    public int mySqrt(int x) {
        int l =0;
        int r =x;
        int res =-1;
        while (l<=r){
            int mid = l+(r-1)/2;
            if ((long) mid*mid <=x ){
                res = mid;
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String ss = "model_v145@rule8,1";
        String[] model_ds = ss.split("@");
        System.out.println(ss);
    }
}
