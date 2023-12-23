# [Delete Nodes And Return Forest][title]

## Solution
通过后序遍历简化根节点的处理，当处理根节点时， 子节点都已经访问结束

遍历过程中， 返回当前节点， 如果被移除则返回 null, 否则是当前节点本身。

深搜过程中，根据当前节点是否是 root 来判断是否要加入到列表中， root 的状态有两种， 1. 起始状态   2. 父节点是否被删了
```kotlin
class Solution {
    private var deleteSet = setOf<Int>()
    private var res = mutableListOf<TreeNode?>()
    
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        deleteSet = to_delete.toSet()
        res = mutableListOf()
        postOrder(root, true)
        return res
    }

    fun postOrder(root: TreeNode?, isRoot: Boolean): TreeNode? {
        root ?: return null
        val isDeleted = root.`val` in deleteSet
        root.left = postOrder(root.left, isDeleted)
        root.right = postOrder(root.right, isDeleted)
        if (isDeleted) {
            return null
        }
        if (isRoot) {
            res.add(root)
        }
        return root
    }
}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/min-cost-climbing-stairs/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
