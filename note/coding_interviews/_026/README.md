# [树的子结构][title]

## Solution
确定空树和空树是否相等
先比较根节点对应是否相等，不等再比较s的左子树和 t的根节点比较，不等再比较s的右子树和 t的根节点比较，不等s就不是t树的子结构
判断两个树是否类似的条件，一定要依题而定，有的是比较到 s 和 t 均为空。本题是比较到 t 为空即可
```kotlin
class Solution {
    fun isSubStructure(s: TreeNode?, t: TreeNode?): Boolean {
        if (s == null || t == null) return false
        return hasSubtree(s, t) || isSubStructure(s.left, t) || isSubStructure(s.right, t)
    }


    private fun hasSubtree(s: TreeNode?, t: TreeNode?): Boolean {
        t ?: return true
        s ?: return false
        return s.`val` == t.`val` &&  hasSubtree(s.left, t.left) && hasSubtree(s.right, t.right)
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
