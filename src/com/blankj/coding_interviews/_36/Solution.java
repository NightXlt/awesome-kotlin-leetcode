package com.blankj.coding_interviews._36;

import com.blankj.structure.TreeNode;

public class Solution {

    TreeNode head, pre;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        convertNode(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void convertNode(TreeNode root) {
        if (root == null) return;
        convertNode(root.left);
        if (pre != null) {
            pre.right = root;
            root.left = pre;
        } else {
            head = root;
        }
        pre = root;
        convertNode(root.right);
    }

    public static void main(String[] args) {
        new Solution().treeToDoublyList(TreeNode.createTestData("[4,2,5,1,3,null,null]"));
    }
}
