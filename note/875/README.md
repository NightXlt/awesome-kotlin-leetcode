# [Koko Eating Bananas][title]

狒狒很喜欢吃香蕉。一天它发现了n堆香蕉，第i堆有piles[i]根香蕉。门卫刚好走开，H小时后才会回来。狒狒吃香蕉喜欢细嚼慢咽，但又想在门卫回来之前吃完所有的香蕉。请问狒狒每小时至少吃多少根香蕉？如果狒狒决定每小时吃k根香蕉，而它在吃的某一堆剩余的香蕉的数目少于k，那么它只会将这一堆的香蕉吃完，下一个小时才会开始吃另一堆的香蕉。
## Solution
虽然还不知道狒狒1小时至少要吃几根香蕉才能在门卫回来之前吃完所有的香蕉，
但知道它吃香蕉的速度的范围。显然，它每小时至少要吃1根香蕉。由于它1小时内只吃一堆香蕉，
因此它每小时吃香蕉数目的上限是最大一堆香蕉的数目，记为 max 根。

狒狒吃香蕉的速度应该在最小值 1 根和最大值 max 根的范围内。在 1～max 根取中间值 mid 根，求出按照每小时吃 mid 根香蕉的速度吃完所有香蕉的时间。如果需要的时间多于H小时，则意味着它应该吃得更快一些，因此狒狒吃香蕉的速度应该在mid+1根到max根这个范围内。
如果需要的时间少于或等于H小时，那么先判断 mid 根是不是最慢的速度。判断的办法是计算如果按照每小时吃 mid - 1 根香蕉的速度需要多久吃完。
如果按照每小时吃mid-1根香蕉的速度需要的时间也小于或等于H小时，就意味着每小时mid根香蕉不是能在H小时吃完所有香蕉的最慢的速度，
因此狒狒吃香蕉的速度应该在1根到mid-1根之间。
如果按照每小时mid-1根香蕉的速度吃完所有香蕉需要的时间大于H小时，这意味着mid根就是能在H小时内吃完所有香蕉的最慢速度。整个过程其实就是在1根到max根之间做二分查找。


```kotlin

 import kotlin.math.ceil
 class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val max = piles.maxOrNull() ?: return -1
        var left = 1
        var right = max
        while (left <= right) {
            val mid = (left + right) shr 1
            // 获取 🦍 吃以该速度吃香蕉所花费时间
            val hours: Int = getHours(piles, mid)
            if (hours <= h) {
                if (mid == 1 || getHours(piles, mid - 1) > h) {
                    return mid
                }
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return -1
    }

    // 下面的算法有点意思， 获取每堆需要多久能吃完时， 这其实是个除法向上取整的过程，我们可以通过 (i + speed - 1) 使其靠近 i / speed 的上界
    // 当然也可以通过 ceil 进行向上取整
    private fun getHours(piles: IntArray, speed: Int): Int {
        return piles.fold(0) { acc, i ->
            acc + (i + speed - 1) / speed
        }
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/koko-eating-bananas/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
