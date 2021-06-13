# [Reorder List][title]

## Solution
和 [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/) 类似,

- 找到中间节点
- 翻转后半段得到链表2，同时将中间节点的next指针置空，防止出现环的情况
- 链表1，2每两个进行合并

```kotlin
    fun reorderList(head: ListNode?): Unit {
    head ?: return
    val mid: ListNode = findMiddleNode(head)
    val l2 = reverseOrder(mid.next)
    mid.next = null
    mergeList(head, l2)
}

private fun mergeList(head: ListNode?, mid: ListNode?) {
    var l1 = head
    var l2 = mid
    var nextTemp1: ListNode?
    var nextTemp2: ListNode?
    while (l1 != null && l2 != null) {
        nextTemp1 = l1.next
        nextTemp2 = l2.next
        l1.next = l2
        l1 = nextTemp1

        // construct swapped node's next pointer
        // 1-> 2 -> 3 -> 4
        // 1-> 2 -> 4 -> 3
        // 1-> 4  2 -> 3
        //     |       ^
        //     |       |
        //     —————————
        l2.next = l1
        l2 = nextTemp2
    }
}

private fun reverseOrder(node: ListNode?): ListNode? {
    var pre: ListNode? = null
    var cur = node
    while (cur != null) {
        val next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}

private fun findMiddleNode(head: ListNode): ListNode {
    var fast = head
    var slow = head
    while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next
        slow = slow.next
    }
    return slow
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/palindrome-linked-list/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
