# [LRU Cache][title]

## Solution
1. 选取数据结构
使用链表模拟访问顺序，这样可以增删节点操作是O(1)的时间复杂度。
   
链表头部元素第一元素是最近使用过的元素，尾巴上的元素是最久未被使用过的元素，当size超过capacity时，优先回收尾巴上的元素，，但是链表会有个问题，就是访问节点的时间复杂度为O(n),

为解决这个问题，使用 Hash 表 记录key和链表节点的映射，这样可达到O(1)时间复杂度进行get操作。

2. 算法
- get

判断 hash 表中是否存在这个元素，存在先将节点移到头部，并返回结果； 否则返回-1表示错误

- put

判断hash表中存在该元素，则直接更新节点的value，并将其移到头部，否则新建一个节点放到头部。
并更新size，更新size后发现size > capacity的话，则移除尾巴上的元素。
  
关于hash表的引入取决于该题是否需要一个数据结构进行get操作O(1)的时间复杂度。
如 两数之和 及 最大不重复子串两道题都利用hash表来实现O(1)的查找操作。
```kotlin
class LRUCache(val capacity: Int) {

    private val cache: MutableMap<Int, DLinkNode> = mutableMapOf()

    private var head = DLinkNode()

    private var tail = DLinkNode()

    private var size = 0

    init {
        head.next = tail
        tail.pre = head
    }

    fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        moveNodeToHead(node)
        return node.value
    }

    private fun moveNodeToHead(dLinkNode: DLinkNode) {
        removeNode(dLinkNode)
        addNodeToHead(dLinkNode)
    }

    private fun removeNode(dLinkNode: DLinkNode?) {
        dLinkNode?.pre?.next = dLinkNode?.next
        dLinkNode?.next?.pre = dLinkNode?.pre
    }

    private fun addNodeToHead(dLinkNode: DLinkNode) {
        dLinkNode.next = head.next
        dLinkNode.pre = head
        head.next?.pre = dLinkNode
        head.next = dLinkNode
    }

    fun put(key: Int, value: Int) {
        val node = cache[key]
        if (node != null) {
            node.value = value
            moveNodeToHead(node)
            return
        }
        val dLinkNode = DLinkNode(key, value)
        size++
        cache[key] = dLinkNode
        addNodeToHead(dLinkNode)
        if (size > capacity) {
            cache.remove(tail.pre?.key)
            removeNode(tail.pre)
            size--
        }
    }

}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/lru-cache/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
