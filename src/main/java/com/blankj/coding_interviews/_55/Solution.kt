package com.blankj.coding_interviews._55

import com.blankj.structure.TreeNode
import kotlin.math.max


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {
    fun maxDepth(root: TreeNode?): Int = if (root == null) {
        0
    } else {
        max(maxDepth(root.left), maxDepth(root.right)) + 1
    }
}
