package com.dougwag.action.hashmap;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.HashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/5/14 7:12 PM
 * @Description:
 */
public class MemorySize {
    private static java.util.HashMap<String, String> needQueryResProductList = new java.util.HashMap<String, String>();
    public static void main(String[] args) throws Exception {

        String userlabel="qwertyuiopasdfghjklzxcvbnm1";
        HashMap map = new HashMap();
        for (int i = 0; i < 10000; i++) {
            String temp ="";
            String stringI =String.valueOf(i);
            if (stringI.length()==1){
                temp ="0000"+stringI;
            }
            if (stringI.length()==2){
                temp ="000"+stringI;
            }
            if (stringI.length()==3){
                temp ="00"+stringI;
            }
            if (stringI.length()==4){
                temp ="0"+stringI;
            }

            map.put("158200"+temp, userlabel+temp);
        }
        String a = new String("aa");
        System.out.println(map.size());
        System.out.println(RamUsageEstimator.humanSizeOf(a));
        System.out.println(RamUsageEstimator.humanSizeOf(map));
        int i = 1;
        Integer ii =new Integer(222222);
        System.out.println(RamUsageEstimator.humanSizeOf(i));
        System.out.println(RamUsageEstimator.humanSizeOf(ii));

    }

}
