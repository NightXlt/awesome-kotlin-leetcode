# [Max Points on a Line][title]

## 思路
统计一条直线上最多点的个数。可以尝试按照斜率进行分组。
遍历当前点和编号大于当前序号的点的斜率， 并记录在 map<斜率，点个数> 中。最终比较 map 中最大点的个数， 并进行更新。

额外需要注意的时， 在计算斜率时得到的是 double， 其进行比较时有精度问题，我们需要把 dy/dx 转化为 int 值 dy + dx * 1001 类似这样的 hash。
但因为考虑到 -1 / 2 == 1 / -2 的， 因此需要保证这两个计算得到的 hash 是一致的， 针对 dy < 0 的情况，统一对 dx, dy 进行一次符号取反操作来保证 hash 一致。

2. 斜率 1/2 == 2 / 4 的， 因此针对每个 dx, dy 需要找到他们的最大公约数并进行消除
3. 如果 dx, dy 有一个等于 0 的情况，说明是水平或者竖直的直线。 直接另一个不为 0 即可得到确定的斜率
4. 剪枝， 当找到一条直线点的个数大与剩余没有访问的点时， 就没必要接着访问了； 当找到一条直线点的个数包含超过一半的点时，说明已经找到最长的直线， 就可以跳出循环了。
5. 如果输入 <= 两个点的情况下， 直接返回 n. 两个点连成一条直线。


```kotlin

import kotlin.math.abs
import kotlin.math.max

class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        val n = points.size
        if (n <= 2) return n
        var res = 0
        for (i in 0..<n) {
            if (res >= n - i || res > n / 2) {
                break
            }
            val map = mutableMapOf<Int, Int>()
            var maxN = 0
            for (j in i + 1..<n) {
                var dx = points[j][0] - points[i][0]
                var dy = points[j][1] - points[i][1]
                when {
                    dx == 0 -> dy = 1
                    dy == 0 -> dx = 1
                    else -> {
                        if (dy < 0) {
                            dx = -dx
                            dy = -dy
                        }
                        val gcdXY = gcd(abs(dx), abs(dy))
                        dx /= gcdXY
                        dy /= gcdXY
                    }
                }
                val key = dy + dx * 1001
                map.merge(key, 1, Integer::sum)
            }
            maxN = (map.values.maxOrNull() ?: 0) + 1
            res = max(maxN, res)
        }
        return res
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b != 0) gcd(b, a % b) else a
    }
}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/max-points-on-a-line/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
