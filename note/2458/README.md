# [Height of Binary Tree After Subtree Removal Queries][title]

## Solution
做这道题之前首先要厘清树的高度和深度区别，深度定义是从上往下数经过的边数，高度定义是从下往上数经过的边数。
对于一颗树，深度是等于高度的，但对于树中的节点不一定相等。
这道题的简介是给定一个二叉树，及一个数组，数组中记录了删除的节点，要求返回删除一个数组记录删除每个节点后树的高度
原理是通过两遍 dfs 来解决，第一遍得到树每个节点的高度，目的是为了在第二遍 dfs 时，利用第一遍算出的结点的高度从而计算出删除该节点后的树的高度。
删除一个节点后，树的深度，要么树到我的深度 - 1，要么是我的兄弟节点的高度，二者取最大值就好。

```kotlin
class Solution {
    var res: IntArray = intArrayOf()
    val heightStore = mutableMapOf<TreeNode?, Int>()
    fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {
        if (root == null) error("tree is empty!")
        if (queries.isEmpty()) error("queries is empty!")
        getHeight(root)
        heightStore[null] = 0
        res = IntArray(heightStore.size)
        dfs(root, -1, 0)
        queries.forEachIndexed { index, value ->
            if (value !in res.indices) error("value is bigger than nodes size")
            queries[index] = res[value]
        }
        return queries
    }

    private fun dfs(
        root: TreeNode?,
        depth: Int,
        restH: Int
    ) {
        root ?: return
        res[root.`val`] = restH
        val dep = depth + 1
        dfs(root.left, dep, max(restH, dep + heightStore.getValue(root.right)))
        dfs(root.right, dep, max(restH, dep + heightStore.getValue(root.left)))
    }

    private fun getHeight(root: TreeNode?): Int {
        root ?: return 0
        val maxChildHeight = max(getHeight(root.left), getHeight(root.right))
        val height = maxChildHeight + 1
        heightStore[root] = height
        return height
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
