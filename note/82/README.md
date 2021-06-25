# [Remove Duplicates from Sorted List II][title]
删除所有重复出现的节点，如 
```
 1，1，1，2，3，4，4 -> 2,3
```

## Solution
通过一个 dummy node 统一对所有节点的操作。

```C++伪代码 
while cur.next && cur.next.next
     if cur.next节点值 == cur.next.next节点值
        x = next.value
        while(cur.next && cur.next.val == x) // 一直删除所有重复的节点(排序链表)
             cur.next = cur.next.next
     else
        cur = cur.next
```

```kotlin
    fun deleteDuplicates(head: ListNode?): ListNode? {
        head ?: return null
        val dummyHead = ListNode(0).apply {
            next = head
        }
        var p = dummyHead
        while (p.next != null && p.next.next != null) {
            if (p.next.`val` == p.next?.next?.`val`) {
                val x = p.next.`val`
                while (p.next != null && p.next.`val` == x) {
                    p.next = p.next?.next
                }
            } else {
                p = p.next
            }
        }
        return dummyHead.next
    }
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
