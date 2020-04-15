package com.dougwang.algorithm_and_datastructure.ds06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:12 PM
 * @Description:
 * 求二叉树的深度
 * https://blog.csdn.net/yangxingpa/article/details/80597780
 */
public class Tree02 {

    //利用递归
    public int TreeDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=TreeDepth(root.left);
        int right=TreeDepth(root.right);
        return left>right?left+1:right+1;
    }

    //利用队列
    public int TreeDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        int deep=0,count=0,nextCount=1;
        //Queue<TreeNode> queue=new Queue<TreeNode>();
        //Solution.java:17: error: Queue is abstract; cannot be instantiated
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode p=queue.poll();
            count++;
            if(p.left!=null){
                queue.add(p.left);
            }
            if(p.right!=null){
                queue.add(p.right);
            }
            if(count==nextCount){
                nextCount=queue.size();
                count=0;
                deep++;
            }
        }
        return deep;
    }

}
