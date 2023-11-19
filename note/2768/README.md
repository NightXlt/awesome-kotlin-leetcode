# [Number of Black Blocks][title]

## Solution
题目一开始就比较难理解, 愿意是矩阵中有一些黑块, 求矩阵中所有 2*2 矩阵中包含黑块的数目.
结果以长度为 5 的 long 数组返回.

这里自己没意识到题意, 2 * 2 的矩阵中至多有 4 个方块是黑色的, 对应结果数组的 index 4.
但同时整个矩阵较大, 如果通过滑动矩阵的方式来模拟, 一定会超时.

因为黑块是有限的, 不妨从黑块出发, 通过 hashmap 记录下所有包含黑块的 2*2 矩阵的坐标, 其坐标可能并不合法,
但这可以接受. 后面统一来做剔除.这里记录坐标使用的 Pair, 因为 pair 通过 data class 实现, 其 hashcode 和元素是相关的.
最后统一对 map 中合理满足 2*2 的坐标进行统计,
所以这里的下标需要满足 i in 0..< m-1, j in 0..<n-1 (2*2 的构成条件)

此外需要注意是, 我们之前遍历统计只计算了包含黑色的块的数目, 针对不包含黑色的块,
可以计算得到总的 2*2 的矩阵数目 - 包含黑色块的块数之和得到的就是不包含黑色块的数目


```kotlin
class Solution {
    fun countBlackBlocks(
        m: Int,
        n: Int,
        coordinates: Array<IntArray>
    ): LongArray {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        val dirs = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, -1),
        )
        for (coordinate in coordinates) {
            for (dir in dirs) {
                val x = coordinate[0] + dir[0]
                val y = coordinate[1] + dir[1]
                val pair = x to y
                map[pair] = map.getOrDefault(pair, 0) + 1
            }
        }
        val res = LongArray(5)
        map.keys.filter { (x, y) ->
            x in 0..<m && y in 0..<n
        }.forEach {
            res[map.getValue(it)]++
        }
        res[0] = (m - 1) * (n - 1) - res.sum()
        return res
    }
}


```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
