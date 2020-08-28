package com.siemens.slaco.itcompact.controller;

import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class HeightofBinaryTree {
    /*
    class Node
    	int data;
    	Node left;
    	Node right;
	*/
    public static int height(Node root) {
        // Write your code here.
        return getThisHeight(root, 0);
    }

    public static int getThisHeight(Node root, int height) {
        if (root.left == null && root.right == null) {
            return height;
        } else if (root.left == null) { // 只有left == null
            return getThisHeight(root.right, height + 1);
        } else if (root.right == null) {
            return getThisHeight(root.left, height + 1);
        } else { // 2边都不null
            int leftH = getThisHeight(root.left, height + 1);
            int rightH = getThisHeight(root.right, height + 1);
            if (leftH > rightH) {
                return leftH;
            } else {
                return rightH;
            }
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
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
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}
