# [Partition Equal Subset Sum][title]
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.


```text
Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

```



## Solution
用函数 f（i，j） 表示能否从前i个物品（物品标号分别为0，1，…，i-1）中选择若干物品放满容量为 j 的背包。如果总共有 n 个物品，背包的容量为 t，那么f（n，t）就是问题的解。
当判断能否从前i个物品中选择若干物品放满容量为 j 的背包时，对标号为i-1的物品有两个选择。一个选择是将标号为i-1的物品放入背包中，如果能从前i-1个物品（物品标号分别为0，1，…，i-2）中选择若干物品放满容量为j-nums[i-1]的背包（即 f（i-1，j-nums[i-1]）为true），那么 f（i，j） 就为true。
另一个选择是不将标号为i-1的物品放入背包中，如果从前i-1个物品中选择若干物品放满容量为j的背包（即f（i-1，j）为true），那么f（i，j）也为true。
```text
f[i][j] = f[i - 1][j] || f[i - 1][j - nums[i - 1]]
```
如果 f（i，j）和 f（i-1，j）可以保存到数组的同一个位置，那么只需要一个一维数组。如果按照从左到右的顺序填充表格， f（i-1，j） 在计算完 f（i，j）之后还可能在计算右边其他值(f（i-1，j-nums[i-1]）) 时被用到，那么不能用f（i，j）替换f（i-1，j）。
但是如果按照从右到左的顺序填充表格，f（i-1，j）在计算完f（i，j）之后就再也不会被用到，f（i-1，j）被f（i，j）替换掉不会引起任何问题。

```kotlin
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.isEmpty()) return true
        val sum = nums.sum()
        if (sum % 2 == 1) {
            return false
        }
        return subsetSum(nums, sum / 2)
    }

    private fun subsetSum(nums: IntArray, target: Int): Boolean {
        val dp = BooleanArray(target + 1)
        dp[0] = true
        for (num in nums) {
            for (j in target downTo num) {
                if (!dp[j]) {
                    dp[j] = dp[j - num]
                }
            }
        }
        return dp[target]
    }
}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/partition-equal-subset-sum/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
