package com.dougwang.algorithm_and_datastructure.ds06;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:12 PM
 * @Description: 二叉树的最大路径和
 * https://blog.csdn.net/u010125432/article/details/104653488
 */
public class Tree04 {
    int max_sum = Integer.MIN_VALUE;

    public int max_gain(TreeNode node) {
        if (node == null) return 0;

        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);

        int price_newpath = node.val + left_gain + right_gain;
        max_sum = Math.max(max_sum, price_newpath);

        return node.val + Math.max(left_gain, right_gain);
    }

    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return max_sum;
    }

}
