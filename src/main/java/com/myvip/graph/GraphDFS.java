package com.myvip.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2021/6/15 10:04 PM
 * @Description: 一条路走到底
 */
public class GraphDFS {

    public static void dfs(Node node){
        if (node ==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next: cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);//把父节点再次放进去,因为是深度遍历，一次只遍历一个邻居。
                    stack.push(next);
                    set.add(next);
                    break;//2
                }

            }
        }
    }
}
