# [Longest Repeating Character Replacement][title]

## Solution
双指针滑动窗口

通过一个 freq 26 长度数组记录窗口的字符频率，
maxCount 记录窗口内出现字符频率最大的次数
遇到 right - left 大于 maxCount + k 时， 移动左指针， 同时更新频率， 此外可以不更新 maxCount， 因为

res 是每一次合理窗口的 right - left + 1

```kotlin
import java.util.*

class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        if (s.length < 2) return s.length
        var left = 0
        var right = 0
        var res = 0
        var maxCount = 0
        val freq = IntArray(26)
        while (right < s.length) {
            freq[s[right] - 'A']++
            maxCount = maxCount.coerceAtLeast(freq[s[right] - 'A'])
            if (right - left + 1 > maxCount + k) {
                freq[s[left] - 'A']--
                left++
            }
            res = res.coerceAtLeast(right - left + 1)
            right++
        }
        return res
    }
}
 

```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/longest-repeating-character-replacement/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
