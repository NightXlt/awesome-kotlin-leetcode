# [1～n 整数中 1 出现的次数][title]

## Solution
这道题更像是一道数学题，找到其中的规律才好解决
遍历当前数字的每一位，根据当前位可以将数字拆成两块
```text
比如204,访问到0，拆成 2 0 4，高位：2， 低位：4
这时十位上包含的1的个数为 2 * 10 = 20（10..19,10个， 110.。119 10个）
那比如是214，这时十位上包含的1的个数为 2 * 10 + 4 + 1 = 25（10..19,10个， 110.。119 10个， 210..214 5个）
那比如是224，这时十位上包含的1的个数为 3 * 10= 30（10..19,10个， 110.。119 10个， 210..219 10个） 
```

```kotlin
class Solution {
    fun countDigitOne(n: Int): Int {
        if (n <= 0) throw IllegalArgumentException("n is less than or equal  0. plz check it")
        var digit = 1
        var high = n / 10
        var cur = n % 10
        var low = 0
        var res = 0
        while (high != 0 || cur != 0) {
            res += when (cur) {
                0 -> {
                    high * digit
                }
                1 -> {
                    high * digit + low + 1 // 1 for 11
                }
                else -> {
                    (high + 1) * digit
                }
            }
            low += cur * digit
            cur = high % 10
            high /= 10
            digit *= 10
        }
        return res
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
