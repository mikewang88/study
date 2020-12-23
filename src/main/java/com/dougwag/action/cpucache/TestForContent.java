package com.dougwag.action.cpucache;

/**
 * @Author: MikeWang
 * @Date: 2020/6/19 10:51 AM
 * @Description:
 */
public class TestForContent {
    static final int LINE_NUM =1024;
    static final int COLUM_NUM =1024;

    public static void main(String[] args) {
        long [][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i =0;i<LINE_NUM;++i){
            for (int j =0;j<COLUM_NUM;++j){
                //array[i][j] = i*2+j;
                array[j][i] = i*2+j;
            }
        }
        long endtime = System.currentTimeMillis();
        long cachetime = endtime -startTime;
        System.out.println("cache time ="+cachetime);
    }
}
