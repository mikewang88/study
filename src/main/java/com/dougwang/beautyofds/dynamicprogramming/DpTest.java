package com.dougwang.beautyofds.dynamicprogramming;

/**
 * @Author: MikeWang
 * @Date: 2020/6/22 4:01 PM
 * @Description:
 * https://blog.csdn.net/u013309870/article/details/69569456
 */
public class DpTest {

    private static int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private static int[][] arr = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};

    public static void main(String[] args) {
        System.out.println(shortestRoad(arr));
        System.out.println(shortestRoad1(arr));
    }
    public static int shortestRoad(int arr[][])
    {
        int dp[][]=new int [arr.length][arr[0].length];
        dp[0][0]=arr[0][0];
        for(int i=1;i<arr.length;i++)
        {
            dp[i][0]=dp[i-1][0]+arr[i][0];
            //第一列只能由上向下
        }
        for(int j=1;j<arr[0].length;j++)
        {
            dp[0][j]=dp[0][j-1]+arr[0][j];
            //第一行只能由左向右
        }
        for(int i=1;i<arr.length;i++)
            for(int j=1;j<arr[0].length;j++)
            {
                dp[i][j]=Math.min(dp[i-1][j], dp[i][j-1])+arr[i][j];
            }

        return dp[arr.length-1][arr[0].length-1];
    }

    public static int shortestRoad1(int arr[][])
    {
        int dp[]=new int[arr[0].length];
        dp[0]=arr[0][0];
        for(int j=1;j<arr[0].length;j++)
        {
            dp[j]=dp[j-1]+arr[0][j];
            //求出第一行的dp
        }
        for(int i=1;i<arr.length;i++)
        {
            dp[0]=arr[i][0]+dp[0];
            //dp[0]代表每一行最左边的dp，
            //后一行的dp覆盖前一行的dp
            for(int j=1;j<arr[0].length;j++)
            {
                dp[j]=Math.min(dp[j-1]+arr[i][j], dp[j]+arr[i][j]);
            }
        }
        return dp[arr[0].length-1];
    }

//    //1. 状态转移表法
//    public static int minDistDP(int[][] matrix, int n) {
//        int[][] states = new int[n][n];
//        int sum = 0;
//        for (int j = 0; j < n; ++j) { // 初始化 states 的第一行数据
//            sum += matrix[0][j];
//            states[0][j] = sum;
//        }
//        sum = 0;
//        for (int i = 0; i < n; ++i) { // 初始化 states 的第一列数据
//            sum += matrix[i][0];
//            states[i][0] = sum;
//        }
//        for (int i = 1; i < n; ++i) {
//            for (int j = 1; j < n; ++j) {
//                states[i][j] =
//                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
//            }
//        }
//        return states[n - 1][n - 1];
//    }
//
//    //2. 状态转移方程法
//    public static int minDist(int i, int j) { // 调用 minDist(n-1, n-1);
//        int[][] mem = new int[4][4];
//        if (i == 0 && j == 0) return matrix[0][0];
//        if (mem[i][j] > 0) return mem[i][j];
//        int minLeft = Integer.MAX_VALUE;
//        if (j - 1 >= 0) {
//            minLeft = minDist(i, j - 1);
//        }
//        int minUp = Integer.MAX_VALUE;
//        if (i - 1 >= 0) {
//            minUp = minDist(i - 1, j);
//        }
//
//        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
//        mem[i][j] = currMinDist;
//        return currMinDist;
//    }
}
