package com.leetcode.day;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2021/1/18 7:01 PM
 * @Description:
 */
public class L830 {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        if (s.length()<2){
            return result ;
        }
        int n = s.length();
        int num = 1;
        int begin = 0;
        int end = 0;
        char flag = s.charAt(0);
        for (int i = 1; i < n; i++) {
            if (flag==s.charAt(i)){
                num++;
                end++;
            }else {
                if (num>2){
                    ArrayList<Integer> params = new ArrayList<>();
                    params.add(begin);
                    params.add(end);
                    result.add(params);
                }
                flag=s.charAt(i);
                begin=i;
                end=i;
                num=1;
            }
        }
        if (num>2){
            ArrayList<Integer> params = new ArrayList<>();
            params.add(begin);
            params.add(end);
            result.add(params);
        }

        return result;
    }

    public static void main(String[] args) {

       // "abbxxxxzzy"
        L830  l830 =  new L830();
        System.out.println(l830.largeGroupPositions("aaa"));
    }
}
