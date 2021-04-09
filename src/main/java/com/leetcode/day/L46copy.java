package com.leetcode.day;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2021/4/5 8:11 PM
 * @Description:
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class L46copy {
    public  List<List<Integer>> permute(int[] nums){
        int len = nums.length;
        List<List<Integer>> res =  new ArrayList<>();
        if (len ==0){
            return  res;
        }

        boolean[] used = new boolean[len];
        Deque<Integer> path =  new ArrayDeque<>();
        dfs(nums,len,0,path,used,res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i =0;i<len;i++){
            if (!used[i]){
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums,len,depth+1,path,used,res);
                used[i] =false;
                path.removeLast();
            }
        }
    }
}
