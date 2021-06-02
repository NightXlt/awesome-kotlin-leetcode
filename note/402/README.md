# [Remove K Digits][title]

## Solution
贪心思想。
维护一个双端单调队列，保证里面的数字都是递增的，如果遇到要添加的元素a小于队尾的元素，不停出队尾元素直至队尾元素小于a.
有种特殊情况需要注意下，当元素序列遍历结束，k 还没消耗完。这时直接将队尾元素出队 k 次即可，因为队尾的元素最大嘛。
之所以是双端队列，因为需要同时操作队首队尾元素。
注意对前导 0 的处理
时间复杂度：O(n)
空间复杂度：O(n)
```kotlin
class Solution {
    fun removeKdigits(num: String, k: Int): String {
        if (num.isBlank() || num.length <= k) {
            return "0"
        }
        val deque: Deque<Char> = LinkedList<Char>()
        var k = k
        for (c in num) {
            while (deque.isNotEmpty() && k > 0 && deque.peekLast() > c) {
                deque.pollLast()
                k--
            }
            deque.addLast(c)
        }
        repeat(k) {
            if (deque.isNotEmpty()) {
                deque.pollLast()
            }
        }
        val res = StringBuilder()
        var leadingZero = true
        while (deque.isNotEmpty()) {
            val digit = deque.pollFirst()
            if (leadingZero && digit == '0') continue
            leadingZero = false
            res.append(digit)
        }
        return if (res.isEmpty()) "0" else res.toString()
    }

}
```

对比下不用辅助栈的代码，更加体会贪心的思想
时间复杂度：O(kn)
空间复杂度：O(1)
```kotlin
class Solution {
        fun removeKdigits(num: String, k: Int): String {
            if (num.isBlank() || num.length <= k) {
                return "0"
            }
            val s = StringBuilder(num)
            for (i in 1..k) {
                var deleteNum = 0
                for (j in s.indices) {
                    if (j == s.length - 1 || s[j] > s[j + 1]) {
                        break
                    }
                    deleteNum++
                }
                s.deleteCharAt(deleteNum)
            }
            while (s.length > 1 && s[0] == '0') {
                s.delete(0, 1)
            }
            return s.toString()
        }


}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/remove-k-digits/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
