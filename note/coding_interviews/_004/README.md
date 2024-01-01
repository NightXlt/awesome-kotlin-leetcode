# [二维数组中的查找][title]

## Solution
我觉的这个老哥讲的一语中的，[评论第一条][link_1]. 从右上角看这个矩阵就是二叉搜索树，左子树都比它小， 右子树都比他大。
留意测试用例的编写逻辑，一个最小值，一个最大值，一个不存在的值， 空数组的情况。kotlin 中可以通过声明避免空数据的情况。 

时间复杂度： O(m+n) m: 行数 n: 列数
空间复杂度： O(1)
```kotlin
class Solution {
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false
        var row = 0
        var col = matrix[0].lastIndex
        while (row < matrix.size && col >= 0) {
            when {
                matrix[row][col] == target -> return true
                matrix[row][col] > target -> col--
                else -> row++
            }
        }
        return false
    }
}

fun main() {
    val matrix = arrayOf(
            intArrayOf(1, 4, 7, 11, 15),
            intArrayOf(2, 5, 8, 12, 19),
            intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24),
            intArrayOf(18, 21, 23, 26, 30)
    )
    // Test cases
    Solution().findNumberIn2DArray(matrix, 1).print() // min values
    Solution().findNumberIn2DArray(matrix, 30).print() // max value
    Solution().findNumberIn2DArray(matrix, 20).print() // don't exist
    Solution().findNumberIn2DArray(emptyArray(), -1).print() // empty array
}

fun Any.print() {
    println(this.toString())
}
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
[link_1]: https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/comments/