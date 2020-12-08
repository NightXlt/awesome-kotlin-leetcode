# [二叉树的后序遍历][title]

## Solution
借用栈特性遍历，后序是左，右，根节点。令 p = 根节点
1. 如果p节点不为空，将p 节点的左子树都入栈
2. 找到最左下的节点
3. 如果有右子树且没有访问过，替换p为左下节点的右子树。重复 1
4. 否则如果右子树为空或者已经访问过(通过 pre 记录)，就把当前节点添加到结果集，并且把当前节点置为已访问过，p=null，防止重复访问
```kotlin
class Solution {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        var p: TreeNode? = root
        val stack = ArrayDeque<TreeNode>()
        val result = mutableListOf<Int>()
        var pre: TreeNode? = null
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.push(p)
                p = p.left
            }
            p = stack.peek()
            val rightChild = p.right
            if (rightChild == null || rightChild == pre) { // 没有右子树/访问过右子树了，把根节点加到结果集里
                result.add(p.`val`)
                pre = p
    // 必须把 p 置为 null，这样 while(p != null)不满足，防止重复访问 p的子树
                p = null 
                stack.pop()
            } else {
                p = rightChild
            }
        }
        return result
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
