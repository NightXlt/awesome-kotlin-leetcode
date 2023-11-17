package com.blankj.medium._146

import com.blankj.ext.print

class LRUCache(private val capacity: Int) {

    private val cachedMap = mutableMapOf<Int, DoubleListNode>()

    val head = DoubleListNode(0, 0)

    val tail = DoubleListNode(0, 0)

    var size = 0

    init {
        head.next = tail
        tail.pre = head
    }

    fun get(key: Int): Int {
        val node = cachedMap[key] ?: return -1
        moveNodeToHead(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (key in cachedMap) {
            val curNode = cachedMap[key]
            moveNodeToHead(curNode)
            curNode?.value = value
            return
        }
        val curNode = DoubleListNode(key, value)
        addNodeToHead(curNode)
        cachedMap[key] = curNode
        size++
        if (size > capacity) {
            cachedMap.remove(tail.pre?.key ?: 0)
            removeNode(tail.pre)
            size--
        }
    }

    private fun moveNodeToHead(curNode: DoubleListNode?) {
        removeNode(curNode)
        addNodeToHead(curNode)
    }

    private fun removeNode(curNode: DoubleListNode?) {
        curNode?.next?.pre = curNode?.pre
        curNode?.pre?.next = curNode?.next
    }

    private fun addNodeToHead(curNode: DoubleListNode?) {
        curNode?.next = head.next
        curNode?.pre = head
        head.next?.pre = curNode
        head.next = curNode
    }

}

class DoubleListNode(
    val key: Int,
    var value: Int
) {
    var next: DoubleListNode? = null
    var pre: DoubleListNode? = null
}


fun main() {
    val lruCache = LRUCache(2)
    lruCache.put(1, 1).print()
    lruCache.put(2, 2).print()
    lruCache.get(1).print()
    lruCache.put(3, 3).print()
    lruCache.get(2).print()
    lruCache.put(4, 4).print()

}
