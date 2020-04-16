package com.dougwang.algorithm_and_datastructure.ds06;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2020/4/16 9:59 AM
 * @Description:
 * 第198题 验证二叉搜索树
 *  * 描述：验证二叉搜索树
 * 思路：中序遍历的结果为有序序列，或者利用本身的性质，左<根<右。（同时分递归和非递归方法）
 */
public class Tree09 {
    //利用中序遍历的结果为有序序列
    public static boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        getSeq(root,list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public static void getSeq(TreeNode root, List<Integer> list){
        if (root == null) {
            return;
        }
        getSeq(root.left,list);
        list.add(root.val);
        getSeq(root.right, list);
    }
    //大佬范例（中序遍历）
    private static TreeNode pre;
    public static boolean isValidBST1(TreeNode root){
        if (root == null) {
            return true;
        }
        boolean l = isValidBST1(root.left);
        boolean c = pre == null || pre.val < root.val;
        pre = root;
        return l&&c&&isValidBST1(root.right);
    }
    //利用性质
    public class Solution {
        public boolean isValidBST2(TreeNode root) {
            if (root == null) return true;
            return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        public boolean valid(TreeNode root, long low, long high) {
            if (root == null) return true;
            if (root.val <= low || root.val >= high) return false;
            return valid(root.left, low, root.val) && valid(root.right, root.val, high);
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(6);
        /*root.left=node1;
        root.right=node2;
        node2.left=node3;
        node2.right=node4;*/
        System.out.println(isValidBST1(root));
    }

}
