# [Substring with Concatenation of All Words][title]

## Solution
令n为字符串的长度，m为数组words的长度（单词的个数），w为单个单词的长度。
由于 words 里面每个单词长度固定，而我们要找的字符串只能恰好包含所有的单词，
因此我们要找的目标子串的长度为 m×w。
那么一个直观的思路是：
1. 使用哈希表 map 记录 words 中每个单词的出现次数 
2. 枚举 w 次， 分别以当前 i 做为起始索引， 往后取得长度为 m×w 的子串 sub 
3. 使用哈希表 Cur 统计 sub 每个单词的出现次数（每隔w长度作为一个单词）
4. 比较 Cur 和 map 是否相同 注意：在步骤3中，如果发现 Sub 中包含了 words 没有出现的单词，可以直接剪枝。 剪枝处使用了带标签的 continue语句直接回到外层循环进行。

```kotlin
class Solution {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        if (words.isEmpty() || s.isEmpty()) return emptyList()
        val m = words.size
        val w = words.first().length
        val n = s.length
        val count = mutableMapOf<String, Int>()
        words.forEach { count[it] = count.getOrDefault(it, 0) + 1 }
        val res = mutableListOf<Int>()
        for (i in 0..<w) {
            // 构建一个当前子串对应的哈希表，统计当前子串中「每个目标单词」的出现次数
            val curMap = mutableMapOf<String, Int>()
            for (j in i..n - w step w) {
                val cur = s.substring(j, j + w)
                // 滑动窗口的大小固定是 m * w，每次将下一个单词添加进 temp，上一个单词移出 temp
                if (j >= i + (m * w)) {
                    val index = j - (m * w)
                    val prev = s.substring(index, index + w)
                    if (curMap[prev] == 1) {
                        curMap.remove(prev)
                    } else {
                        curMap[prev] = curMap.getValue(prev) - 1
                    }
                }
                curMap[cur] = curMap.getOrDefault(cur, 0) + 1
                if (cur in count && curMap[cur] == count[cur] && curMap == count) {
                    res.add(j - (m - 1) * w)
                }
            }
        }
        return res
    }
}
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
