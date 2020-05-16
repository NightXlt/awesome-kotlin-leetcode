package com.blankj.medium._04_06;

import com.blankj.structure.TreeNode;

public class Solution {

    public TreeNode inorderSuccessorTraversal(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.right != null) {
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }
        TreeNode res = null;
        while (root != p) {
            if (root.val < p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.val >= root.val) return inorderSuccessor(root.right, p);
        TreeNode left = inorderSuccessor(root.left, p);
        return left != null ? left : root;
    }

}

