package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/12 2:01 PM
 * @Description: 封闭岛屿的个数
 */
public class L1254 {

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(closedIsland(grid));
    }

    public static int closedIsland(int[][] grid) {
        int count=0;
        int val=1;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length;j++)
            {
                if(grid[i][j]==0)
                {
                    val= 1;
                    dfs(grid,i,j,val);
                    count+=val;
                }
            }
        }
        return count;
    }
    private static void dfs(int[][] grid,int i,int j,int val)
    {
        if(i<0||i==grid.length||j<0||j==grid[0].length)
        {
            val=0;
            return;
        }
        if(grid[i][j]!=0)return ;
        grid[i][j]=1;
        dfs(grid,i+1,j,val);
        dfs(grid,i-1,j,val);
        dfs(grid,i,j-1,val);
        dfs(grid,i,j+1,val);
    }

//    作者：ppppjcute
//    链接：https://leetcode-cn.com/problems/number-of-closed-islands/solution/java-dfssou-suo-by-ppppjqute-2/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


//    public static int closedIsland(int[][] grid) {
//        if (grid == null || grid[0] == null) {
//            return 0;
//        }
//        int res = 0;
//        int rn = grid.length;
//        int cn = grid[0].length;
//        for (int r = 0; r < rn; r++) {
//            for (int c = 0; c < cn; c++) {
//                if (grid[r][c] == 0) {
//                    res++;
//                    dfs(grid, r, c);
//                }
//            }
//        }
//        return res;
//    }
//
//    private static void dfs(int[][] grid, int r, int c) {
//        //判断 base case
//        if (!inArea(grid, r, c)) {
//            return;
//        }
//        if (grid[r][c] == 1) {
//            return;
//        }
//        grid[r][c] = 2;
//
//        dfs(grid, r - 1, c);
//        dfs(grid, r + 1, c);
//        dfs(grid, r, c - 1);
//        dfs(grid, r, c + 1);
//    }
//
//    private static boolean inArea(int[][] grid, int r, int c) {
//        return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length;
//    }
}
