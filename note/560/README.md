# [Subarray Sum Equals K][title]

## Solution
通过一个 Map 记录 0..i 的和sum，当遍历 sum 时，如果发现 sum - k 存在于 map中，那么必存在 0..j 的和 = sum - k。
map 中 0 to 1 的键值对初始化是为了漏解， 因为在刚好 sum == k 时， 如果 map 中没有记录结果就会漏掉这一个子数组
那么 j..i 的和即为 k， count 即是 map\[sum - k\]

```kotlin
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map = mutableMapOf(0 to 1)
        for (num in nums) {
            sum += num
            // key: sum   value: count
            if (map.containsKey(sum - k)) {
                count += map[sum - k]!!
            }
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return count
    }
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/subarray-sum-equals-k/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
