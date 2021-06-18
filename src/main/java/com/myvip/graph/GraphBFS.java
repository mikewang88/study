package com.myvip.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: MikeWang
 * @Date: 2021/6/15 9:53 PM
 * @Description:
 */
public class GraphBFS {

    public static void bfs(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set =  new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next: cur.nexts){
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
