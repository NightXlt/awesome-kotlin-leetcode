# [Palindrome Linked List][title]

## Solution
一开始自己的想法很单纯，反转链表，再直接比较，但这样需要 O(n)的空间通过头插法来复制链表。要求是 O(1)

题眼是回文串，把从中间节点的后半段反转后，再从头和后半段头结点逐个比较，如果一致就是回文串。栗子：
1221， 反转后 1212，比较 1==1，2==2. 比较结束后要记得反转回来呀！！！

通过快慢指针找到中间节点，把中间节点后面的后半段链表进行反转，一半的链表进行逐节点比较。比较节点结束后再将节点反转回来。
```kotlin
class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) return true
        val midNode = findMiddleNode(head)
        val secondHalfStart = reverseOrder(midNode.next)
        var result = true
        var p1 = head
        var p2 = secondHalfStart
        while (p2 != null) {
            if (p1?.`val` != p2.`val`) {
                result = false
                break
            }
            p1 = p1.next
            p2 = p2.next
        }
        midNode.next = reverseOrder(secondHalfStart)
        return result
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
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/palindrome-linked-list/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
