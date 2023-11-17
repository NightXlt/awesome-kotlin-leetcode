# [Flatten a Multilevel Doubly Linked List][title]


## 思路
展平的规则是一个节点的子链展平之后将插入该节点和它的下一个节点之间。由于子链表中的节点也可能有子链表，因此这里的链表是一个递归的结构。在展平子链表时，
如果它也有自己的子链表，那么它嵌套的子链表也要一起展平。
嵌套子链表和外层子链表的结构类似，可以用同样的方法展平，因此可以用递归函数来展平链表。递归代码如下所示：

```kotlin
class Node(var `val`: Int) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null
}

class Solution {
    fun flatten(root: Node?): Node? {
        if (root == null) return root
        flattenReturnTail(root)
        return root
    }

    private fun flattenReturnTail(root: Node?): Node? {
        var node = root
        var tail: Node? = null
        while (node != null) {
            val next = node.next
            if (node.child != null) {
                val child = node.child
                val childTail = flattenReturnTail(child)
                node.child = null
                node.next = child
                child?.prev = node
                childTail?.next = next
                if (next != null) {
                    next.prev = childTail
                }
                tail = childTail
            } else {
                tail = node
            }
            node = node.next
        }
        return tail
    }
}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
