package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/24 9:25 PM
 * @Description: 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class L5 {
    public String longestPalindrome2(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l=0;l<n;l++){
            for (int i=0;i+l<n;i++){
                int j=i+l;
                if (l==0){
                    dp[i][j] = true;
                }else if (l==1){
                    dp[i][j]=(s.charAt(i) == s.charAt(j));
                }else {
                    //在这一步分类讨论（根据头尾字符是否相等）
                    dp[i][j]=(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
                }
                if (dp[i][j] && l+1>ans.length()){
                    ans = s.substring(i,i+l+1);
                }
            }
        }
        return ans;

        //边界条件是：表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，即 j - 1 - (i + 1) + 1 < 2 ，整理得 j - i < 3。

    }

    public String longestPalindrome(String s){
        //特判
        int len = s.length();
        if (len<2){
            return s;
        }
        int maxLen =1;
        int begin =0;

        //dp[i][j] 表示 s[i][j] 是否回文
        boolean[][] dp =  new boolean[len][len];
        char[] charArray = s.toCharArray();

        //初始值
        for (int i=0;i<len;i++){
            dp[i][i] =true;
        }

        for (int j =1;j<len;j++){
            for (int i=0;i<j;i++){
                if (charArray[i]!=charArray[j]){
                    dp[i][j] =false;
                }else {
                    if (j-i<3){
                        dp[i][j] =true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j-i+1>maxLen){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
}
