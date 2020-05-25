# [Maximum Number of Vowels in a Substring of Given Length][title]

## Solution
采用滑动窗口解决，窗口大小为 k, 每次滑动时判断两端的值对 count 进行加减，最终对 count 取最大值即可
```kotlin
import kotlin.math.max

class Solution {
    fun maxVowels(s: String, k: Int): Int {
        var maxLen = 0
        val vowels = "aeiou"
        var i = 0
        var count = 0
        for (j in s.indices) {
            if (s[j] in vowels) count++
            if (j > k - 1) {
                if (s[i] in vowels) count--
                i++
            }
            maxLen = max(maxLen, count)
        }
        return maxLen
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
