# [Construct Quad Tree][title]

## 思路
矩阵代表四叉树，正方形区域的值全相同的时候，那么这个区域代表一个叶节点，否则不是叶节点，它的叶节点就是四分对应矩阵区域所代表的的子树，
这里计算矩形面积的思路， 可以借鉴 304 那道题的。

此外就是深搜的部分了， 提过前缀和快速得到整体矩形的面积， 如果都是统一的， 那么直接返回该节点， value 为区域是否 == 矩形面积， 是叶子节点
，
否则进行递归计算 topLeft, topRight, bottomLeft, bottomRight. value 无所谓， 不是叶子节点， 因为有子树了。

```kotlin
class NumMatrix(matrix: Array<IntArray>) {

    val sum = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }

    init {

        for (i in matrix.indices) {
            var rowSum = 0
            for (j in matrix[0].indices) {
                rowSum += matrix[i][j]
                sum[i + 1][j + 1] = sum[i][j + 1] + rowSum
            }
        }
    }

    // 画图减去前缀和叠加部分， 注意左上角的点移动是因为考虑到矩阵第一行需要保留，
    // 因此整体左上角偏移了一格
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return sum[row2 + 1][col2 + 1] + sum[row1][col1] -
                sum[row2 + 1][col1] - sum[row1][col2 + 1]
    }

}

```

## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]


[title]: https://leetcode.cn/problems/construct-quad-tree/description/?envType=study-plan-v2&envId=top-interview-150

[ajl]: https://github.com/Blankj/awesome-java-leetcode
