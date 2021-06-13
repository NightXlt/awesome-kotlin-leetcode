# [Recover Binary Search Tree][title]

## Solution
因为二叉搜索树中序遍历是递增的，很直接就有一个方案， 中序遍历树顺序记录在数组中，遍历数组中逆序对即可。
时间复杂度：O(n)
空间复杂度：O(n) 数组长度 = 元素个数

另一个方案是通过记录其前序节点，发现两次 当前节点小于前序节点时，分别记录下第一次的前序节点和第二次的当前节点，交换即得结果

为啥需要两次呢， 这里有些费解，一次不行吗？ 因为做这道题没有纸笔，只能在脑子想有点乱。
以test case root = \[1,3,null,null,2\]举例。 
他的遍历顺序是 3，2，1。 如果当一次遍历到 2 < 3时， 就进行交换节点，那么得到的结果就是2 3 1还是有逆序对。

那么问题是出在哪里呢？ 我们无法确定首次出现的逆序对是否是因为后序节点放错位置导致的。比如 3 和 2的逆序其实是因为 3和1放错位置导致。
因此在第一次发现逆序对，不能直接判断找到了目标的两个节点，必须找到两次逆序对。
```text
first：pre = 3 root = 2  
errorNode1 = pre errorNode2 = root

second：pre = 2 root = 1   
errorNode2 = root; break 
```
或者只找到一次逆序对，但是我遍历结束树，就可以直接交换找到的两个错误节点了

时间复杂度：O(n)
空间复杂度：O(Height(tree)) 树高，最坏情况退化为链表


对于 `ArrayDeque` 栈的操作 push, pop
                  队列的操作 add, poll
```kotlin
    fun recoverTree(root: TreeNode?) {
        var root = root
        val stack: Deque<TreeNode> = ArrayDeque<TreeNode>()
        var pred: TreeNode? = null
        var errorNode1: TreeNode? = null
        var errorNode2: TreeNode? = null
        while (stack.isNotEmpty() || root != null) {
            while (root != null) {
                stack.push(root)
                root = root.left
            }
            root = stack.pop()
            if (pred != null && root!!.`val` < pred.`val`) {
                errorNode2 = root
                if (errorNode1 == null) {
                    errorNode1 = pred
                } else {
                    break
                }
            }
            pred = root
            root = root?.right
        }
        errorNode1?.`val` = errorNode2?.`val`?.also { errorNode2.`val` = errorNode1!!.`val` }
    }

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/recover-binary-search-tree/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
