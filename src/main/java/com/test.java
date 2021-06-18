package com;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: MikeWang
 * @Date: 2020/6/27 10:18 AM
 * @Description:
 */
public class test {
    public static void main(String[] args) {
//        double rate = Double.valueOf("65336") / Double.valueOf("199996");
//        BigDecimal b = new BigDecimal(rate).setScale(4, BigDecimal.ROUND_HALF_UP);
//        System.out.println(b); }
        // 内容
        //String value = "{{familyName}}{{sex}}你好，剩余额度为:{{loanLeftBehindAmount}}";
        String value ="33dd";

//        String skh ="(?<=\\{)[^\\}]+";//用于匹配《》里面的文字
//        String str="是的没错《要匹配这里》是你要找的吗";
//        Pattern pattern=Pattern.compile(skh);
//        Matcher matcher=pattern.matcher(value);
//        boolean is=matcher.find();
//        if(is){
//            System.out.print(matcher.group());
//        }

        //String value = "PerformanceManager[第1个中括号]Product[第2个中括号]<[第3个中括号]79~";
        List<String> list = extractMessage(value);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+"-->"+list.get(i));
        }

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {// 如果数组为空
            return "";
        }
        int length = strs[0].length();//第一个元素的长度
        int count = strs.length;//数组的长度
        for (int i = 0; i < length; i++) {//循环第一个的长度
            char c = strs[0].charAt(i);//取出i位置的元素
            for (int j = 1; j < count; j++) {//从数组第二个位置开始循环
                //
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }


    /**
     * 提取中括号中内容，忽略中括号中的中括号
     * @param msg
     * @return
     */
    public static List<String> extractMessage(String msg) {

        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '{') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == '}') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        list.forEach(e->{
            if (e.contains("{") && e.contains("}")){
                result.add(e.replace("{","").replace("}",""));
            }
        });
        return result;
    }


}
