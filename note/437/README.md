# [Path Sum III][title]

## Solution
基于前缀和思路, 利用一个 map 存储当前根节点到当前树的节点的和以及其该和出现的次数,
如果访问一个节点时的前缀和为 curSum, 而此时存在另一个节点 targetSum - curSum 在 map.
那么说明我们找到了一段目标路径, 这段目标路径的 count = map[targetSum - curSum] ?: 0
此外还需要访问左右子树, 累加他们的 count.
此外 test case 中可能存在 int 相加越界问题,可以通过 Long 解决

```kotlin
class Solution {
    // map stored root-node sum to sum's count
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        root ?: return 0
        val map = mutableMapOf<Long, Int>(0L to 1)
        return dfs(root, targetSum, map, 0)
    }

    private fun dfs(
        root: TreeNode?,
        targetSum: Int,
        map: MutableMap<Long, Int>,
        path: Long
    ): Int {
        root ?: return 0
        val curSum = path + root.`val`
        var count = map.getOrDefault(curSum - targetSum, 0)
        map[curSum] = map.getOrDefault(curSum, 0) + 1
        count += dfs(root.left, targetSum, map, curSum)
        count += dfs(root.right, targetSum, map, curSum)
        map[curSum] = map.getValue(curSum) - 1
        return count
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/path-sum-iii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
