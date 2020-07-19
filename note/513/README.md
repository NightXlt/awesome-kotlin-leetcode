# [找树左下角的值][title]

## Solution
1. DFS
因为最左边的值必然是位于最左侧的最长路径上的叶子节点，通过深搜遍历出每条根节点到叶子节点的路径，维护一个全局变量记录最长 depth, 以及 最左边的值。
这样即使有若干条最长路径也可以只保存第一次遍历的最长路径的叶子节点
2. BFS
这个方法就很骚了， 普通的 BFS 是从上至下， 从左至右。 这道题的 BFS 解法是从上至下，从右至左。
这样做的好处是遍历到最后一行的最后一个元素时，必然是最左侧的元素。妙啊，这样做就是在基本算法上做了一定的迁移，考察了编码人员的迁移知识能力。不止可以如此，我们还可以访问右下角的节点也是同理。
```kotlin
class Solution {

    private var leftestValue = -1
    var maxDepth = 0

    fun findBottomLeftValue(root: TreeNode?): Int {
        dfs(root, 1)
        return leftestValue
    }

    fun dfs(root: TreeNode?, depth: Int) {
        val root = root ?: return
        if (depth > maxDepth) {
            maxDepth = depth
            leftestValue = root.`val`
        }
        dfs(root.left, depth + 1)
        dfs(root.right, depth + 1)
    }

    fun findBottomLeftValueByBFS(root: TreeNode?): Int {
        val root = root ?: throw IllegalArgumentException("root must be not null")
        var queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var result = root
        while (queue.isNotEmpty()) {
            result = queue.removeFirst()
            result.right?.run { queue.add(this) }
            result.left?.run { queue.add(this) }
        }
        return result.`val`
    }

}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/find-bottom-left-tree-value/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
