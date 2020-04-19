package com.dougwang.algorithm_and_datastructure.ds06;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:13 PM
 * @Description:
 * 二叉树的最低公共父结点
 * https://blog.csdn.net/zangdaiyang1991/article/details/94563586
 */
public class Tree06 {
    /**
     * 思路：
     * 1、在左、右子树中分别查找是否包含p或q，如果（两种情况：左子树包含p，右子树包含q/左子树包含q，右子树包含p），
     *   那么此时的根节点就是最近公共祖先
     * 2、如果左子树包含p和q，那么到root->left中查找，最近公共祖先在左子树里面
     * 3、如果右子树包含p和q，那么到root->right中查找，最近公共祖先在右子树里面
     * 4、注意：不可能left和right的返回值同时都是nullptr
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left!=null && right!=null) {
            return root;
        }

        return left == null ? right : left;
    }

    /**
     * 思路(非递归)：
     * 1、找到root->p的路径
     * 2、找到root->q的路径
     * 3、两条路径求最后一个相交节点
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        List<TreeNode> pPath = findPath(root, p);
        List<TreeNode> qPath = findPath(root, q);

        TreeNode common = null;
        for (int i=0, j=0; i<pPath.size() && j<qPath.size(); i++,j++) {
            if (pPath.get(i) == qPath.get(j)) {
                common = pPath.get(i);
            }
        }

        return common;
    }

    private List<TreeNode> findPath(TreeNode root, TreeNode node) {
        List<TreeNode> path = new ArrayList<>();
        dfs(root, node, new ArrayList<>(), path);
        return path;
    }

    private void dfs(TreeNode root, TreeNode node, List<TreeNode> tmp, List<TreeNode> path) {
        if (root == null) {
            return;
        }

        tmp.add(root);

        if (root == node) {
            path.addAll(new ArrayList<>(tmp));
        }

        dfs(root.left, node, tmp, path);
        dfs(root.right, node, tmp, path);

        tmp.remove(tmp.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        TreeNode left = new TreeNode(3);
        root.left = left;
        System.out.println(new Tree06().lowestCommonAncestorII(root, left, right).val);

    }
}