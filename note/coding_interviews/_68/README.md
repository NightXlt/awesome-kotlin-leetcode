#[二叉树的下一个节点][title]

## Solution
中序遍历的下一个节点，这似乎在考研过程中看过这道题。善用给到的父节点线索。
伪代码逻辑是

```伪代码
if node.right != null
    // find the leftest child of node.right
    node = node.right
    while node.left != null
         node = node.left
    return node
else if node.parent != null
    parent = node.parent
    // find first parent containes node as its left child
    while node is parent right child
        node = parent
        parent = parent.parent
    return parent

```

```kotlin
    fun nextNode(target: TreeNode?): TreeNode? {
    target ?: return target
    var next: TreeNode? = null
    if (target.rightChild != null) {
        var rightChild = target.rightChild
        while (rightChild?.leftChild != null) {
            rightChild = rightChild.leftChild
        }
        next = rightChild
    } else if (target.parent != null) {
        var cur = target
        var parent = target.parent
        while (parent != null && cur == parent.rightChild) {
            cur = parent
            parent = parent.parent
        }
        next = parent
    }
    return next
}

class TreeNode(private val `val`: Int) {

    var leftChild: TreeNode? = null

    var rightChild: TreeNode? = null

    var parent: TreeNode? = null
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
