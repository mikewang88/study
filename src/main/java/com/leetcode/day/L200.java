package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/10 11:25 AM
 * @Description:
 */
public class L200 {
    public int countIsLands(int[][] c){
        if (c==null ||c[0]==null){
            return 0;
        }
        int n = c.length;
        int m = c[0].length;
        int res = 0;

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (c[i][j]==1){
                    res++;
                    //感染
                    infect(c,i,j,n,m);
                }
            }
        }
        return res;
    }

    public void infect(int[][] c,int i,int j,int n,int m){
        if (i<0||i>=n||j<0||j>=m||c[i][j]!=1){
            return;
        }
        c[i][j]=2;
        infect(c,i+1,j,n,m);
        infect(c,i-1,j,n,m);
        infect(c,i,j+1,n,m);
        infect(c,i,j-1,n,m);
    }
}
