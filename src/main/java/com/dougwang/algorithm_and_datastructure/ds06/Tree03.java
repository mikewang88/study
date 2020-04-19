package com.dougwang.algorithm_and_datastructure.ds06;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:12 PM
 * @Description:
 * 二叉树的递归、非递归遍历
 * https://blog.csdn.net/u011514810/article/details/75907170
 */
public class Tree03 {
    ////前序遍历
    //递归实现
    public static void preOrder(TreeNode root){
        if(root != null){
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static ArrayList preOrder1(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList alist = new ArrayList();
        TreeNode p = root;
        while(p != null || !stack.empty()){
            while(p != null){
                alist.add(p.val);
                stack.push(p);
                p = p.left;
            }
            if(!stack.empty()){
                TreeNode temp = stack.pop();
                p = temp.right;
            }
        }
        return alist;
    }
}
