package com.blankj.coding_interviews._35

import com.blankj.coding_interviews._004.print
import java.util.*


class Solution {

//    fun verifyPostorder(postorder: IntArray): Boolean {
//        return partition(postorder, 0, postorder.lastIndex)
//    }
//
//    private fun partition(postorder: IntArray, start: Int, end: Int): Boolean {
//        if (end - start <= 1) return true
//        val root = postorder[end]
//        var p = start
//        while (postorder[p] < root) p++
//        val mid = p
//        while (postorder[p] > root) p++
//        return p == end && partition(postorder, start, mid - 1) && partition(postorder, mid, end - 1)
//    }

    // Monotonic stack
    fun verifyPostorder(postorder: IntArray): Boolean {
        if (postorder.isEmpty()) return true
        var root = Int.MAX_VALUE
        val stack = ArrayDeque<Int>()
        for (i in postorder.indices.reversed()) {
            // post[i] must be root's left child, so if (postorder[i] > root) return false
            if (postorder[i] > root) return false
            while (stack.isNotEmpty() && stack.peek() > postorder[i]) { // find out nearest root of postorder[i]
                root = stack.pop()
            }
            stack.push(postorder[i])
        }
        return true
    }
}

fun main() {
    Solution().verifyPostorder(intArrayOf()).print() // empty tree
    Solution().verifyPostorder(intArrayOf(8)).print() // one node
    Solution().verifyPostorder(intArrayOf(8, 6, 5)).print() // just right child
    Solution().verifyPostorder(intArrayOf(5, 6, 8)).print() // just left child
    Solution().verifyPostorder(intArrayOf(1, 6, 3, 2, 5)).print()
    Solution().verifyPostorder(intArrayOf(1, 3, 2, 6, 5)).print()
    Solution().verifyPostorder(intArrayOf(1,2,5,10,6,9,4,3)).print()

}
