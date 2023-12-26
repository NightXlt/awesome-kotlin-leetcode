# [Count K-Subsequences of a String With Maximum Beauty][title]

## Solution
提示1
思考：k 1要怎么做？
从出现次数最多的字符中选一个字母。

提示 2
思考：k=2要怎么做？
从出现次数最多的开始选，如果有多个出现次数最多的呢？
例如= aaaabbbbcccc, k=2，那么需要从3种字母中选种，每种都有4个字符可以选（题目
说相同字符组成的子序列也算不同的），所以方案数为 4^k * C(3,k) (数学组合公式)


提示 3
统计每个字符出现次数的个数，然后从大到小遍历次数c及其个数 num。 (TreeMap 实现)

•如果 num ＜ k，那么这 c 种字符每种选一个，方案数为 c^num，然后将k減去 num。
•如果 num ≥ k，根据上面的讨论，方案数为 c^k * C(num, k)。
所有方案数相乘即为答案。

```kotlin
import java.util.*

class Solution {
    fun countKSubsequencesWithMaxBeauty(s: String, k: Int): Int {
        val count = IntArray(26)
        s.forEach { count[it - 'a']++ }
        val map = TreeMap<Int, Int>()
        count.filter { it != 0 }
            .forEach { map.merge(it, 1, Integer::sum) }
        var res = 1L
        var residual = k
        for (e in map.descendingMap().entries) {
            val (charFreqs, num) = e
            if (num >= residual) {
                return (res * pow(charFreqs, residual) % MOD * comb(num, residual) % MOD).toInt()
            }
            res = res * pow(charFreqs, num) % MOD
            residual -= num
        }
        return 0
    }

    private fun comb(num: Int, residual: Int): Long {
        var res = num
        var n = num - 1
        for (i in 2..residual) {
            res = res * n / i
            n--
        }
        return res % MOD
    }

    private fun pow(x: Int, n: Int): Long {
        var res = 1L
        var exp = n
        var base = x.toLong()
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = res * base % MOD
            }
            base = base * base % MOD
            exp /= 2
        }
        return res
    }

    companion object {
        const val MOD: Long = (1e9 + 7).toLong()
    }
}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/count-k-subsequences-of-a-string-with-maximum-beauty/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
