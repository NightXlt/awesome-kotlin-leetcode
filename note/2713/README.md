# [Maximum Strictly Increasing Cells in a Matrix][title]

题目指的是从 一个点可以横向，纵向跳到一个更大的元素上， 最长的长度是多少。

## Solution
按元素值从小往大计算。 定义 f[i] 表示到达 mat[i][j] 时所访问的单元格的最大数量。那么答案就是所有 f[i][j] 的最大
值。
如何计算 f[i][j] 从哪转移过来？
请注意，我们不需要知道具体从哪个单元格转移过来，只需要知道转移来源的f的最大值是多少。

按照元素值从小往大计算，那么第 i 行的比 mat[i][j] 小的f值都算出来了，大于等于 mat[i][j] 的尚未计算，视作0。
所以对于第 i 行，相当于取这一行的f值的最大值，作转移来源的值。我们用一个长为 m 的数组 rowMax 维护每一行的f值的最大值。
对于每一列，也同理，用一个长为n的数组 colMax 维护。

所以有 f[i][j] = max（rowMax[i]， colMax[j]） + 1

这里加一是把 mat[i][j] 算上。

代码实现时 f[i][j] 可以省略，因为只需要每行每列的f值的最大值。
对于相同元素，在全部计算出最大值后，再更新到 rowMax 和 colMax 中。
此外在 Java 中我们通过 TreeMap 维护值的递增，我们只需要关注 row 和 col 的最大值变化即可。

时间复杂度：O(mnlogn)，其中 m 和 n 分别为的行数和列数。瓶颈在排序上。
空间复杂度：O(mn)

```kotlin
class MaxIncreasingCells {
    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        val graph = TreeMap<Int, MutableList<IntArray>>()
        for (i in mat.indices) {
            for (j in mat.first().indices) {
                // 相同元素放在同一组，统计位置
                graph.getOrPut(mat[i][j]) { mutableListOf() }
                    .add(intArrayOf(i, j))
            }
        }
        val rowMax = IntArray(mat.size)
        val colMax = IntArray(mat.first().size)
        var res = 0
        for (positions in graph.values) {
            val mx = IntArray(positions.size)
            for (i in positions.indices) {
                // 先把最大值算出来，再更新 rowMax 和 colMax
                mx[i] = max(rowMax[positions[i][0]], colMax[positions[i][1]]) + 1
                res = max(res, mx[i])
            }

            for (k in positions.indices) {
                // position 的 k 映射到 rowMax 的 row, colMax 的 col
                val i = positions[k][0]
                val j = positions[k][1]
                rowMax[i] = max(mx[k], rowMax[i])
                colMax[j] = max(mx[k], colMax[j])
            }
        }
        return res
    }
}


```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
