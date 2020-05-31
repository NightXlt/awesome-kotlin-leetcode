# [Flatten Binary Tree to Linked List][title]

## Solution
1. 我们需要将右子树 R 取下来，将 左子树 L 放到 root.right位置。
2. 将右子树 R 放到左子树 L 的右下角位置
3. root = root.night 迭代至 root 为空
图解可参考[链接](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/)
```kotlin

class Solution {
    fun flatten(root: TreeNode?): Unit {
        var node = root
        while (node != null) {
            val leftChild = node.left
            if (leftChild == null) {
                node = node.right
                continue
            }
            var pre = leftChild
            while (pre?.right != null) pre = pre.right
            pre?.right = node.right
            node.right = leftChild
            node.left = null
            node = node.right
        }
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
