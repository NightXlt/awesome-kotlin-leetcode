# [Strobogrammatic Number III][title]


## Solution

类似 247 做法， 遍历所有可能的 strobogrammatic， 如果满足在 low 和 high 之前的数字， 加入列表，

最后记得从列表中移除前缀 0 的字符串

```kotlin
class Solution {
    fun strobogrammaticInRange(low: String, high: String): Int {
        val res = findStrobogrammaticWithPrefixZero(high.length, low.toLong(), high.toLong())
        return res.filterNot {
            it.length > 1 && it.first() == '0'
        }.size
    }

    private fun findStrobogrammaticWithPrefixZero(n: Int, low: Long, high: Long) : List<String>{
        val queue = ArrayDeque<String>()
        val start = n % 2
        val res = mutableListOf<String>()
        queue.addAll(listOf("", "0", "1", "8"))
        for (i in start..<n step 2) {
            val size = queue.size
            repeat(size) {
                val str = queue.removeFirst()
                var prevStrobogrammatic = str.toLongOrNull()
                if (prevStrobogrammatic in low..high) {
                    res.add(str)
                }
                if ((prevStrobogrammatic ?: 0) > high) {
                    return res
                }
                queue.add("0" + str + "0")
                queue.add("1" + str + "1")
                queue.add("6" + str + "9")
                queue.add("8" + str + "8")
                queue.add("9" + str + "6")
            }
        }
        res.addAll(queue.filter { it.toLongOrNull() in low..high })
        return res
    }

}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]


[title]: https://leetcode-cn.com/problems/meeting-rooms-ii/

[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
