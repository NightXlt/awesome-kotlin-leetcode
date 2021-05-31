# [The Skyline Problem][title]

## Solution
归并的思想。当只有一个块(x, y, h)时比较好理解，将 （x, h）, (y, 0) 添加到结果集中。当有两个块合并时遵循下列规则
伪代码如下
```
(x1, y1) (x3, y3)
    i
(x2, y2) (x4, y4)
    j

    while (i < len1 || j < len2) {
        if x1 < x2 then
            x = x1
            i++
            h1 = y1
        if x1 > x2 then
            x = x2
            j++
            h2 = y2
        else 
            x = x1
            i++
            j++
            h1 = y1
            h2 = y2
        h = max(h1, h2)
    
        if res.isEmpty || h != res last element y then
            res.add (x, h)
    }
    
```
合并时通过 i,j 双指针移动，每次移动都会取 x1，x2 最小的坐标, 同时分别记录下当前的 y 值，如h1, h2 
h取两端区间的h1，h2的最大值。
为了保证不重复添加，每次加入前判断 h 是否不等于前一次加入结果集的 y，如果是的话，才添加进去
为了防止 i,j 越界问题，当i越界时，将其 x 置为最大值，这样可以顺序遍历j指针结束

```kotlin
class Solution {
    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        if (buildings.isEmpty()) return emptyList()
        return divide(buildings, 0, buildings.lastIndex)
    }

    private fun divide(
            buildings: Array<IntArray>,
            start: Int,
            end: Int
    ): List<List<Int>> {
        val res: MutableList<List<Int>> = mutableListOf()
        if (start == end) {
            res.add(
                    listOf(
                            buildings[start][0],
                            buildings[start][2]
                    )
            )
            res.add(
                    listOf(
                            buildings[start][1],
                            0
                    )
            )
            return res
        }
        val mid = (start + end) / 2
        val skyLineLeftPart = divide(buildings, start, mid)
        val skyLineRightPart = divide(buildings, mid + 1, end)
        merge(skyLineLeftPart, skyLineRightPart, res)
        return res
    }

    private fun merge(
            skyLineLeftPart: List<List<Int>>,
            skyLineRightPart: List<List<Int>>,
            res: MutableList<List<Int>>
    ) {
        var i = 0
        var j = 0

        var h1 = 0
        var h2 = 0
        while (i < skyLineLeftPart.size || j < skyLineRightPart.size) {
            val x1 = skyLineLeftPart.getOrNull(i)?.get(0)?.toLong() ?: Long.MAX_VALUE
            val x2 = skyLineRightPart.getOrNull(j)?.get(0)?.toLong() ?: Long.MAX_VALUE
            var x: Long
            when {
                x1 < x2 -> {
                    h1 = skyLineLeftPart[i][1]
                    x = x1
                    i++
                }
                x2 < x1 -> {
                    h2 = skyLineRightPart[j][1]
                    x = x2
                    j++
                }
                else -> {
                    h1 = skyLineLeftPart[i][1]
                    h2 = skyLineRightPart[j][1]
                    x = x1
                    i++
                    j++
                }
            }
            val height = max(h1, h2)
            if (res.isEmpty() || height != res.last()[1]) {
                res.add(
                        listOf(
                                x.toInt(),
                                height
                        )
                )
            }
        }
    }
}

```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/the-skyline-problem/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
