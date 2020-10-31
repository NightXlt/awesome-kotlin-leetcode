# [二叉树的镜像][title]

## Solution
递归是利用辅助栈特性，实现了递归栈的调用。栈后进先出， 每次拿出一个节点node将其左右子树塞入栈中，
再将node 的左右子树进行交换直至栈为空即可。
```kotlin
class Solution {
    //    fun mirrorTree(root: TreeNode?): TreeNode? {
//        root ?: return root
//        val temp = root.left
//        root.left = root.right
//        root.right = temp
//        root.left.apply {
//            mirrorTree(this)
//        }
//        root.right.apply {
//            mirrorTree(this)
//        }
//        return root
//    }
    fun mirrorTree(root: TreeNode?): TreeNode? {
        root ?: return root
        val stack = ArrayDeque<TreeNode?>()
        stack.add(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            node?.left?.apply { stack.add(this) }
            node?.right?.apply { stack.add(this) }
            val temp = root.left
            root.left = root.right
            root.right = temp
        }
        return root
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
