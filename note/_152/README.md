# [Maximum Product of Word Lengths][title]

## Solution
之前的 maxProduct 似乎换了题目内容， 由数字变了字符串
针对数字有正有负的情况是记录最大最小值， dp 求解

针对字符串如何快速判断有无公共字符呢？ 
- 位图， 记录每个字符串 26 个小写字母出现的位图为 x， 如果两个 x & 下来为 0, 表明是没有公共字符

time complexity: O(n^2)
space complexity: O(n)


```kotlin
    fun maxProduct(words: Array<String>): Int {
        val flags = IntArray(words.size)
        for (i in words.indices) {
            for (j in words[i].indices) {
                flags[i] = flags[i] or (1 shl (words[i][j] - 'a'))
            }
        }
        var res = 0
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (flags[i] and flags[j] == 0 && words[i].length * words[j].length > res) {
                    res = words[i].length * words[j].length
                }
            }
        }
        return res
    }

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/single-number-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
