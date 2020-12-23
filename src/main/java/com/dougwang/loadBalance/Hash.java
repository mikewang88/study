package com.dougwang.loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: MikeWang
 * @Date: 2020/6/30 2:55 PM
 * @Description:
 */
public class Hash
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

        // 在Web应用中可通过HttpServlet的getRemoteIp方法获取
        String remoteIp = "127.0.0.1";
        int hashCode = remoteIp.hashCode();
        int serverListSize = keyList.size();
        int serverPos = hashCode % serverListSize;

        return keyList.get(serverPos);
    }
}
