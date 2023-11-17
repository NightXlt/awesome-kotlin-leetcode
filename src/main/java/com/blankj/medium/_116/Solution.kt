package com.blankj.medium._116


class Solution {
    fun connect(root: Node?): Node? {
        if (root == null) return root
        var leftmost = root
        while (leftmost != null) {
            var head = leftmost
            while (head != null) {
                head.left?.next = head.right
                head.right?.next = head.next?.left
                head = head.next
            }
            leftmost = leftmost.left
        }
        return root
    }
}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

fun main() {

}