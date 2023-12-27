# [Candy][title]

## Solution
分别进行两次遍历， 从左到右中记录当前的糖果数量，然后再从右往左遍历， 取当前数量和左边数量的最大值。因为要满足当前同学要比两边都多

左遍历时， 需要将结果记录在数组中， 但右遍历时就不需要了， 遍历到当前数量， 直接求结果。

```kotlin
class Solution {
    fun candy(ratings: IntArray): Int {
        val left = IntArray(ratings.size)
        left[0] = 1
        for (i in 1..<ratings.size) {
            left[i] = if (ratings[i] > ratings[i - 1]) {
                left[i - 1] + 1
            } else {
                1
            }
        }
        var right = 1
        var res = max(left.last(), right)
        for (i in ratings.lastIndex - 1 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                right++
            } else {
                right = 1
            }
            res += max(right, left[i])
        }
        return res
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
