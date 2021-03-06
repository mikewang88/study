package com.myvip.sortn;

import java.util.HashMap;

/**
 * @Author: MikeWang
 * @Date: 2021/4/27 10:09 PM
 * @Description:
 */
public class TrieTree {


    public static class Node{
        public int pass;
        public int end;
        public HashMap<Integer, Node> nexts;
        public Node(){
            pass =0;
            end =0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie{
        private Node root;
        public Trie(){
            root =  new Node();
        }

        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass++;
            int index = 0;//路径
            for (int i= 0; i<chars.length;i++){
                index = (int) chars[i];
                if (!node.nexts.containsKey(index)){//下一层的路径中不包括
                    node.nexts.put(index,new Node());
                }
                node = node.nexts.get(index);//当前节点
                node.pass++;//当前节点++
            }
            //循环结束后，说明单词已经遍历完
            node.end++;
        }

        public void delete(String word){
            if (serch(word)!=0){
                Node node = root;
                int index = 0;
                char[] chars = word.toCharArray();
                for (int i=0;i<chars.length;i++){
                    index = (int)chars[i];
                    if (--node.nexts.get(index).pass == 0){
                        node.nexts.remove(index);//删除节点，防止内存泄露
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        //word 这个单词之前加入了几次
        private int serch(String word) {
            if (word == null){
                return 0;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i= 0; i<chars.length;i++) {
                index = (int) chars[i];
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;//有几个结尾，就加入了几次
        }

        //所有加入的字符串中，有几个是以pre 这个字符串作为前缀的

        public int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node node = root;
            int index =0;
            for (int i=0;i<chars.length;i++){
                if (!node.nexts.containsKey(index)){
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;//有几个单词经过 就有几个
        }

    }

}
