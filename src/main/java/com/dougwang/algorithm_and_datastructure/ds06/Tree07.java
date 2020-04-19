package com.dougwang.algorithm_and_datastructure.ds06;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:13 PM
 * @Description:
 * 求二叉树k层的结点个数
 * https://www.javazhiyin.com/50095.html
 */
public class Tree07 {
    public int getKLevelNodeNum(TreeNode node, int k) {
        if (node == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int left = getKLevelNodeNum(node.left, k-1);
        int right = getKLevelNodeNum(node.right, k-1);
        return left + right;
    }
}
