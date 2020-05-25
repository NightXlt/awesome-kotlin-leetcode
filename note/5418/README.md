# [Pseudo-Palindromic Paths in a Binary Tree][title]

## Solution
Pseudo-Palindromic： 伪回文，遍历的从根到叶子节点的路径可能并不直接是回文串，但可以重新组合为回文串
自己当时的想法是通过长度为 10 的数组记录每条路径上出现的数字次数，遍历到叶子节点时，判断数组中出现的数字次数是否均为偶数或者只有一个数字次数为奇数放在中间。
看了题解，嗨呀，位运算自己又给忘了，
对于有限的状态如数字，字母出现次数可通过位图表示状态， 通过异或判断奇偶性。
标识位中某一位出现 1表示出现次数为 奇数， 0 为偶数。最后如果标识为 0，或者其中仅有一个 1即可以组成回文串。
```kotlin
import kotlin.math.max

class Solution {
    var res = 0

    fun pseudoPalindromicPaths(root: TreeNode?): Int {
        root ?: return 0
        dfs(root, 0)
        return res
    }

    private fun dfs(root: TreeNode, cur: Int) {
        val cur = cur xor (1 shl root.`val`)
        if (root.left == null && root.right == null) {
            if (cur == 0 || cur and (cur - 1) == 0) res++
            return
        }
        root.left?.run { dfs(this, cur) }
        root.right?.run { dfs(this, cur) }
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
