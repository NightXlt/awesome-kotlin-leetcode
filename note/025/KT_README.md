# [Reverse Nodes in k-Group][title]

## Solution
思路太直接了，明显双指针移动K 个节点 + 反转链表的知识。依次遍历K 个节点后，反转链表直至访问链表节点为空

搬运下柯基的图
以例子中的 `k = 3` 为例，其 `pre` 和 `tail` 如下所示。
```
0->1->2->3->4->5
|           |
pre        tail
```
记录下 pre的next 节点作为下一次反转的pre节点
反转 head = pre.next 到 tail 节点。顺序遍历相邻元素反转得到
```
0  3->2->1->4->5
|        |   
pre     pre.next
```
完成 pre.next 指向 tail 节点 3 得到
```
0->3->2->1->4->5
|        |   
pre     上一次的pre.next
```
这时再移动 pre和 tail 指针
```
0 3->2->1->4->5
        |  |
       pre tail
```

```kotlin
class ReverseKGroup {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 1) return head
        val dummyHead = ListNode(0)
        var tail = head
        var pre: ListNode? = dummyHead
        dummyHead.next = head
        while (tail != null) {
            repeat(k - 1) {
                tail = tail?.next ?: return dummyHead.next
            }
            val nextHead = pre?.next
            reverseNode(pre, tail)
            pre?.next = tail
            pre = nextHead
            tail = nextHead?.next
        }
        return dummyHead.next
    }

    private fun reverseNode(pre: ListNode?, tail: ListNode?) {
        var preNode: ListNode? = tail?.next
        var curNode = pre?.next
        while (preNode != tail) {
            val nextNode = curNode?.next
            curNode?.next = preNode
            preNode = curNode
            curNode = nextNode
        }
    }
}

```

柯基的代码看起来会简单些，他用的反转链表方法是另一种，依次把需要链表的元素查到 dummy 节点后面，这样得到的链表也是反转好的了。
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/lru-cache/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
