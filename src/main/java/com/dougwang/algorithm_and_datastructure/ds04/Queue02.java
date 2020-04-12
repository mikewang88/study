package com.dougwang.algorithm_and_datastructure.ds04;


import java.util.*;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:19 PM
 * @Description:
 * BFS使用队列
 * https://blog.csdn.net/qq_37638061/article/details/89598413
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Queue02 {
    //深度优先
    private static List<Integer> dfsresult = new ArrayList<>();
    public static List<Integer> DFSByRecursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        /*
        处理节点的逻辑(由于是递归的调用，定义ArrayList的时候不能写在方法内部)
         private static List<Integer> result = new ArrayList<>();
         我把 此处的 result 的定义为了一个全局变量
         */
        dfsresult.add(root.val);

        if (root.left != null) {
            DFSByRecursion(root.left);
        }
        if (root.right != null) {
            DFSByRecursion(root.right);
        }
        return dfsresult;
    }

    //广度优先
    public static List<Integer> BFSByQueue(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        //存放遍历结果，然后返回
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();

            /*
            处理 TreeNode 节点 的逻辑
             */
            result.add(treeNode.val);


            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode12 = new TreeNode(12);

        treeNode.left = treeNode2;
        treeNode.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode5.left = treeNode9;
        treeNode6.left = treeNode10;

        treeNode7.left = treeNode11;
        treeNode7.right = treeNode12;

        List<Integer> result = BFSByQueue(treeNode);

        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

    }

}
