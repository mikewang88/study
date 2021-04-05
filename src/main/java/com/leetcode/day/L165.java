package com.leetcode.day;


import javafx.util.Pair;

/**
 * @Author: MikeWang
 * @Date: 2021/3/9 4:11 PM
 * @Description:
 */
public class L165 {
    class Solution {
        public int compareVersion(String version1, String version2) {
            String[] str1=version1.split("\\.");
            String[] str2=version2.split("\\.");
            int len=Math.min(str1.length,str2.length);
            int i=0;
            for( i=0;i<len;i++){
                if(Integer.valueOf(str1[i])>Integer.valueOf(str2[i]))return 1;
                if(Integer.valueOf(str1[i])<Integer.valueOf(str2[i]))return -1;
            }
            if(str1.length!=str2.length){
                if(str1.length>str2.length){
                    for(;i<str1.length;i++){
                        if(Integer.valueOf(str1[i])!=0)return 1;
                    }
                    return 0;
                }else{
                    for(;i<str2.length;i++){
                        if(Integer.valueOf(str2[i])!=0)return -1;
                    }
                    return 0;
                }
            }
            return 0;
        }
    }
}
