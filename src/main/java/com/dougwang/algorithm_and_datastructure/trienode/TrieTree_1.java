package com.dougwang.algorithm_and_datastructure.trienode;

import java.util.ArrayList;

//
//https://blog.csdn.net/yuhk231/article/details/51539840
/*
 * 无数据结构设计下的蛮力中文键树
 */
class TrieNode {
    public String value;
    public ArrayList<TrieNode> ptr = null;
    public TrieNode(String value) {
        this.value=value;
        ptr =new ArrayList<TrieNode>();
    }
}

public class TrieTree_1 {
    private static TrieNode root = null;
    ArrayList<String> searchResult=new ArrayList<String>();
    StringBuffer tempWord=new StringBuffer();
    int start=0;

    public TrieTree_1() {
        root = new TrieNode(null);
    }

    public void insert(String key) {
        TrieNode p = root;
        String tempWord;
        boolean contains;
        TrieNode tempNode;
        for (int i = 0; i < key.length(); i++) {
            tempWord=String.valueOf(key.charAt(i));
            contains=false;
            for(TrieNode tn:p.ptr){
                if(tn.value.equals(tempWord)){
                    p=tn;
                    contains=true;
                    break;
                }
            }
            if(!contains){
                tempNode=new TrieNode(tempWord);
                p.ptr.add(tempNode);
                p=tempNode;
            }
        }
    }

    public ArrayList<String> search(String key) {  //模糊查询就是这个方法，打个比方比如key是"ap"，那么ArrayList里就有{"apple","application"}
        TrieNode p = root;
        String temp;
        boolean contains=false;
        for (int i = 0; i < key.length(); i++) {
            temp=String.valueOf(key.charAt(i));
            contains=false;
            for(TrieNode tn:p.ptr){
                if(tn.value.equals(temp)){
                    p=tn;
                    contains=true;
                    break;
                }
            }
            if(contains){
                continue;
            }else{
                break;
            }
        }
        if(contains){
            if(!(p.ptr.isEmpty())){
                //查找到关键字
                searchResult.clear();
                tempWord.delete(0, tempWord.length());
                tempWord.append(key);
                start=key.length();
                traverseTree(p);
            }else{
                //已经查找到键树的底部
                return null;
            }
        }else{
            //没有查找到相应关键字
            return null;
        }
        return searchResult;
    }

    private void traverseTree(TrieNode p){
        if(!(p.ptr.isEmpty())){
            for(TrieNode tn:p.ptr){
                tempWord.append(tn.value);
                start++;
                traverseTree(tn);
                start--;
                tempWord.delete(start,tempWord.length());
            }
        }else{
            searchResult.add(tempWord.toString());
        }
    }
    public static void main(String[] args) {
        TrieTree_1 chinese = new TrieTree_1();
        chinese.insert("中");
        chinese.insert("中国人");
        chinese.insert("中国");
        chinese.insert("中华人民");
        chinese.insert("中华人崛起");
        chinese.insert("中华上下五千年");
        ArrayList<String> list = chinese.search("中华");
        for (String string : list) {
            System.out.println(string);
        }
    }
}


