package com.siemens.slaco.itcompact.controller;

import java.util.*;
import java.io.*;

class Node1 {
    Node1 left;
    Node1 right;
    int data;

    Node1(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    private static boolean found = false;
    /*
    class Node1
        int data;
        Node1 left;
        Node1 right;
    */
    public static Node1 lca(Node1 root, int v1, int v2) {
        // Write your code here.
        if (root == null) {
            return null;
        } else { // 探索一下root下面的left branch & right branch, 以及root本身
            // 先看看左branch
            Node1 left = lca(root.left, v1, v2);
            // 找到了！直接返回
            if (found) {
                return left;
            }
            // 再看看right branch
            Node1 right = lca(root.right, v1, v2);
            if (found) {
                return right;
            }

            // 若左右分别满足v1和v2，直接return此root
            // 或者左or右满足其中一个v，root满足另一个，return root
            // 否则return left or right or root中满足的那个Node1
            boolean leftOK = (left != null);
            boolean rightOK = (right != null);
            boolean rootOK = (root.data == v1 || root.data == v2);
            // 左右分别满足v1和v2
            boolean case1 = (leftOK && rightOK && left.data != right.data);
            // 左or右满足其中一个v，root满足另一个
            boolean case2 = (leftOK && rootOK && left.data != root.data);
            boolean case3 = (rightOK && rootOK && right.data != root.data);
            if (case1 || case2 || case3) {
                found = true;
                return root;
            } else if (leftOK) {
                return left;
            } else if (rightOK) {
                return right;
            } else if (rootOK) {
                return root;
            } else {
                return null;
            }
        }
    }

    public static Node1 insert(Node1 root, int data) {
        if(root == null) {
            return new Node1(data);
        } else {
            Node1 cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node1 root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node1 ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }
}
