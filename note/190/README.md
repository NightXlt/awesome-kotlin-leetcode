# [Reverse Bits][title]


## Solution
先提暴力的方法：
模拟逆转的过程， 循环右移一位元素， 并把他放到 31 - i 的相反位置上即可。
指的注意的是，如果发现 n == 0 时， 就没必要继续该过程了， 0 或上任何数， 还是任何数本身。

Time Complexity: O(logN): 移位的时间复杂度是 logN
Space complexity: O(1)

```kotlin
class Solution {
    // you need treat n as an unsigned value
    fun reverseBits(n: Int): Int {
        var res = 0
        var cur = n
        for (i in 0 ..<32) {
            if (cur == 0) break
            res = res or ((cur and 1) shl (31 - i))
            cur = cur ushr 1
        }
        return res
    }
}
```


更优的方案， 是基于分治的思想，
我们把 32 位分为 16 位 和 16 位，并进行交换，
再递归处理前面 16 位中的 8 位和 8 位交换，
递归进行该行为直至 1 为止。
Time Complexity: O(1)
Space complexity: O(1)

// Int 最大值是 0x7ffffff, 所以 0xffff0000 是会超过最大值的， 需要把 n 转为 Long 防止溢出。
// 找对应交换的数字时， 慢慢找规律， 能找出来的。16 和 16 交换， 8 和 8 交换， 4 和 4 交换， 2 和 2 交换， 1 和 1 交换。
```kotlin
    fun reverseBitsWithLessTime(n: Int): Int {
        var n = n.toLong()
        n = ((n and 0xffff0000) ushr 16) or ((n and 0x0000ffff) shl 16)
        n = ((n and 0xff00ff00) ushr 8) or ((n and 0x00ff00ff) shl 8)
        n = ((n and 0xf0f0f0f0) ushr 4) or ((n and 0x0f0f0f0f) shl 4)
        n = ((n and 0xcccccccc) ushr 2) or ((n and 0x33333333) shl 2)
        n = ((n and 0xaaaaaaaa) ushr 1) or ((n and 0x55555555) shl 1)
        return n.toInt()
    }
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]


[title]: https://leetcode.cn/problems/reverse-bits/description/?envType=study-plan-v2&envId=top-interview-150

[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
