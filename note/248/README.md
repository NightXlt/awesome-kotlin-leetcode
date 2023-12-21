# [Strobogrammatic Number III][title]


## Solution

类似 247 做法， 遍历所有可能的 strobogrammatic， 如果满足在 low 和 high 之前的数字， 加入列表，

最后记得从列表中移除前缀 0 的字符串

```kotlin
class Solution {
    fun findStrobogrammatic(n: Int): List<String> {
        if (n < 1) return emptyList()
        val res = findStrobogrammaticWithPrefixZero(n)
        return res.filterNot {
            it.length > 1 && it.first() == '0'
        }
    }

    private fun findStrobogrammaticWithPrefixZero(n: Int): MutableList<String> {
        val queue = ArrayDeque<String>()
        val start = n % 2
        // 确定首位从哪里开始
        if (start == 0) {
            queue.add("")
        } else {
            queue.addAll(listOf("0", "1", "8"))
        }
        for (i in start..<n step 2) {
            val size = queue.size
            repeat(size) {
                val prevStrobogrammatic = queue.removeFirst()
                queue.add("0" + prevStrobogrammatic + "0")
                queue.add("1" + prevStrobogrammatic + "1")
                queue.add("6" + prevStrobogrammatic + "9")
                queue.add("8" + prevStrobogrammatic + "8")
                queue.add("9" + prevStrobogrammatic + "6")
            }
        }
        return queue.toMutableList()
    }
}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]


[title]: https://leetcode-cn.com/problems/meeting-rooms-ii/

[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
