# [Maximum Length of Semi-Decreasing Subarrays][title]

## Solution
TreeMap 是有序的平衡二叉搜索树， 我们维护一个值和索引都递增的序列，对于每个数组元素，尝试在 map 中找到比他的元素， 如果存在的话，对应索引相减就是这个索引的最大区间。所有区间的最大值就是结果

怎么维护值和索引都递增呢？ 只有当 nums[i] 比 treeMap 中最大元素都大时， 才可以加入到 map 中。 由于是顺序遍历索引自然而然就是递增的啦。

```kotlin
import java.util.*
import kotlin.math.max

class Solution {
    fun maxSubarrayLength(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        val map = TreeMap<Int, Int>()
        var res = 0
        map[nums[0]] = 0
        for (i in 1..<nums.size) {
            val higherKey = map.higherKey(nums[i])
            if (higherKey != null) {
                res = max(res, i - map.getValue(higherKey) + 1)
            }
            if (nums[i] > map.lastKey()) {
                map[nums[i]] = i
            }
        }
        return res
    }
}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/employee-free-time/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
