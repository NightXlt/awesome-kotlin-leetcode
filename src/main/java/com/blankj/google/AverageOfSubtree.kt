package com.blankj.google

import com.blankj.structure.TreeNode

class AverageOfSubtree {

    var count = 0
    fun averageOfSubtree(root: TreeNode?): Int {
        dfs(root)
        return count
    }

    private fun dfs(root: TreeNode?): Pair<Int, Int> {
        root ?: return (0 to 0)
        val (leftSum, leftNum) = dfs(root.left)
        val (rightSum, rightNum) = dfs(root.right)
        val sum = leftSum + rightSum + root.`val`
        val num = leftNum + rightNum + 1
        val average = sum / num
        if (average == root.`val`) {
            count++
        }
        return sum to num
    }
}

fun main() {

}