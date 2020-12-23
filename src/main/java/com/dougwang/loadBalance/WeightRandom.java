package com.dougwang.loadBalance;

import java.util.*;

/**
 * @Author: MikeWang
 * @Date: 2020/6/30 3:13 PM
 * @Description:
 */
public class WeightRandom
{
    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++)
                serverList.add(server);
        }

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());

        return serverList.get(randomPos);
    }
}
