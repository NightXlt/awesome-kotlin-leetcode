# [Minimum Number of People to Teach][title]

## Solution
贪心的思想，找到没有共同语言可以沟通的朋友 减去 这群人中大家会的课数最多的数目就是需要教的最大数目了

```
class Solution {
    private fun hasCommon(languages: Array<IntArray>, friend1: Int, friend2: Int): Boolean {
        val x = languages[friend1 - 1]
        val y = languages[friend2 - 1]
        return x.any { y.contains(it) }
    }

    fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
        val mostLanguages = IntArray(n)
        val noConnects = mutableSetOf<Int>()
        for ((friend1, friend2) in friendships) {
            if (!hasCommon(languages, friend1, friend2)) {
                noConnects.add(friend1)
                noConnects.add(friend2)
            }
        }
        for (noConnectMan in noConnects) {
            languages[noConnectMan - 1].forEach {
                mostLanguages[it - 1]++
            }
        }
        val max = mostLanguages.maxOrNull() ?: 0
        return noConnects.size - max
    }
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/minimum-number-of-people-to-teach/description/?company_slug=duo-lin-guo
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
