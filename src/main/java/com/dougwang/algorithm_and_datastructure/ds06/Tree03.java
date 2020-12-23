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
    ////前序遍历 根-左-右
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

    //中序遍历 左-根-右
    public static void inOrder(TreeNode root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static ArrayList inOrder1(TreeNode root){
        ArrayList alist = new ArrayList();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while(p != null || !stack.empty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.empty()){
                TreeNode temp = stack.pop();
                alist.add(temp.val);
                p = temp.right;
            }
        }
        return alist;
    }

    //后续遍历 左-右-根
    public static void postOrder(TreeNode root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static ArrayList postOrder1(TreeNode root){
        ArrayList alist = new ArrayList();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return alist;
        TreeNode cur,pre = null;
        stack.push(root);
        while(!stack.empty()){
            cur = stack.peek();
            if((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))){
                TreeNode temp = stack.pop();
                alist.add(temp.val);
                pre = temp;
            }
            else{
                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            }
        }
        return alist;
    }
}
