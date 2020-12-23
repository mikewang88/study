package com.dougwang.loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: MikeWang
 * @Date: 2020/6/30 2:54 PM
 * @Description:
 */
public class Random
{
    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }
}
