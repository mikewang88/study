package com.myvip.treehome;

/**
 * @Author: MikeWang
 * @Date: 2021/5/21 8:29 PM
 * @Description:
 */
public class Code03_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //节点的可以得到的信息
    public static class Info {
        boolean isBST;
        public int min;
        public int max;

        public Info(boolean is, int mi, int ma) {
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    //开始组装节点返回信息 （重要）
    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        //设置可能性
        boolean isBST = false;
        if (
                (leftInfo == null ? true : (leftInfo.isBST && leftInfo.max < head.value))
                        &&
                        (rightInfo == null ? true : (rightInfo.isBST && rightInfo.min > head.value))
                ) {
            isBST = true;
        }
        return new Info(isBST, min, max);
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }
}
