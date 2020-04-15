package com.dougwang.algorithm_and_datastructure.ds06;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:12 PM
 * @Description:
 * 判断一棵树是否平衡
 */
public class Tree01 {
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)return true;
        if(IsBalanced(root) == -1)return false;
        return true;
    }
    public int IsBalanced(TreeNode root){
        if(root == null) return 0;
        int left = IsBalanced(root.left);
        int right = IsBalanced(root.right);
        int diff = Math.abs(left - right);
        if(left == -1 || right == -1 || diff > 1)return -1;
        return (left > right)?left+1:right+1;
    }

}

class TreeNode{
    int val;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
