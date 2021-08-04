# [Populating Next Right Pointers in Each Node II][title]

类似 116 题通过上层的 next 节点构建下层的 next节点关系
但因为二叉树并非是完美二叉树，所以需要记录下层的第一个节点 nextLayerStartNode 在哪，
以及下层上次访问的节点 previousNode

因为二叉树并非是完美二叉树，所以 nextLayerStartNode 不是简单的等于 p.left。 p 可能没有左子树，但有右子树。
```kotlin
class Solution {
    private var previousNode: Node? = null
    private var nextLayerStartNode: Node? = null

    fun connect(root: Node?): Node? {
        if (root == null) return root
        var start = root
        while (start != null) {
            previousNode = null
            nextLayerStartNode = null
            var p = start
            while (p != null) {
                if (p.left != null) {
                    handle(p.left)
                }
                if (p.right != null) {
                    handle(p.right)
                }
                p = p.next
            }
            start = nextLayerStartNode
        }
        return root
    }

    private fun handle(p: Node?) {
        previousNode?.next = p
        if (nextLayerStartNode == null) {
            nextLayerStartNode = p
        }
        previousNode = p
    }
}
```

时间复杂度： O(n) 空间复杂度：O(1)

也可以使用 BFS 访问， 时间复杂度： O(n) 空间复杂度：O(n)

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
