# [Similar String Groups][title]

## Solution
一个是关于学生之间的朋友关系，一个是关于字符串的相似关系，但从本质上来看是同一类问题，都是求图中子图的数目。
通过遍历比较数组 i 和 i 后面元素的相似， 相似则将其分组，如果能归为一组， 则子图数目减一。

```kotlin
class Solution {
    fun numSimilarGroups(strs: Array<String>): Int {
        if (strs.isEmpty()) return 0
        val find = IntArray(strs.size) { it }
        var count = strs.size
        for (i in strs.indices) {
            for (j in i + 1 until strs.size) {
                if (similar(strs[i], strs[j]) && merge(find, i, j)) {
                    count--
                }
            }
        }
        return count
    }

    private fun similar(s: String, s1: String): Boolean {
        var diffCount = 0
        for (i in s.indices) {
            if (s[i] != s1[i]) diffCount++
        }
        return diffCount <= 2
    }

    private fun merge(find: IntArray, x: Int, y: Int): Boolean {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            return true
        }
        return false
    }

    private fun find(find: IntArray, x: Int): Int {
        var index = x
        while (find[index] != index) {
            index = find[index]
        }
        return index
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/similar-string-groups/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
