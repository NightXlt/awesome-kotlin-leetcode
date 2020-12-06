# [复杂链表的复制][title]

## Solution
两年前的一道老题了，自己忘的都差不多咯。
再次记录下这道题. 之后看下代码就想起来了。
看到复杂链表的结构和正常的链表结构不一样，这就比较陌生了，尝试根据知识的已知点推未知点。
通过分解问题，先去解决已知的问题，正常链表复制，顺序遍历一遍，就可以创建出一条一模一样的链表了
难的点在于 random 指针 怎么复制，常规情况下，得到一个 random 节点，我们需要再次从头到尾再次遍历一次链表从而找到 random 节点，再进行复制，从而导致了 O(N^2)的时间复杂度
这是第一种方案，跟面试官讲明后，提出优化方案，空间换时间，通过 HashMap 记录 random 出现的位置，避免遍历。这样只需要两边遍历即可
时间复杂度：O(N^2)
空间复杂度：O(N)

如果面试官不满意 O(N)的空间复杂度，那就通过拼接+拆分的方法，很像高中画辅助线的方法
在每个节点后面拼上一个一模一样的节点，再构建其 next, random 节点。最终将其拆成两个链表。就可以得到一份 copy 链表了。
```
1. 
    原链表： 1 -> 2 -> 3 -> null
    拷贝链表：1 -> 1 -> 2 -> 2 -> 3 -> 3 -> null
2. 拷贝random 节点关系
3. 拆分链表并构建拷贝链表的 next 指针关系
```
 
```java
class Solution {
/*    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }*/

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        cloneNodes(head);
        connectSiblingNodes(head);
        return reconnectNextNode(head);
    }
    /*
    这个 reconnectNodes 是个错误示例，不能简单一次 while 循环；因为 cur.next是可能为空的. cur.next.next 可能导致 NPE.
    需要两个节点一个遍历原链表cur，一个遍历拷贝链表cloneNode。让cur 先走一步，使其位于 cloneNode 的后一个节点，避免 NPE.
    以 1 -> 1 -> 2 -> 2 -> 3 -> 3 -> null栗子，cur 先走一步到2，cloneNode 在第二个 1 节点的位置，然后 cur，cloneNode 每次走两步，完成 next 操作.这样也不会 NPE了
    private Node reconnectNodes(Node head) {
        Node cur = head;
        Node cloneHead = head.next;
        while (cur != null) {
            Node cloneNode = cur.next;
            cur.next = cloneNode.next;
            cloneNode.next = cur.next.next;
            cur = cloneNode.next;
        }
        return cloneHead;
    }
    */
    private Node reconnectNextNode(Node head) {
        Node cur = head;
        Node cloneHead = null;
        Node cloneNode = null;
        if (cur != null) {
            cloneHead = cloneNode = cur.next;
            cur.next = cloneHead.next;
            cur = cur.next;
        }
        while (cur != null) {
            cloneNode.next = cur.next;
            cloneNode = cloneNode.next;
            cur.next = cloneNode.next;
            cur = cur.next;
        }
        return cloneHead;
    }

    private void connectSiblingNodes(Node head) {
        Node cur = head;
        while (cur != null) {
            Node cloneNode = cur.next;
            Node sibling = cur.random;
            if (sibling != null) {
                cloneNode.random = sibling.next; // 构建复制节点的 next
            }
            cur = cloneNode.next;
        }
    }

    private void cloneNodes(Node head) {
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node cloneNode = new Node(cur.val);
            cur.next = cloneNode;
            cloneNode.next = next;
            cur = next;
        }
    }

    public static void main(String[] args) {
        //
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/copy-list-with-random-pointer/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
