package com.blankj.medium._146

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import java.util.*

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

class DLinkNode {

    var key: Int = 0
    var value: Int = 0

    var pre: DLinkNode? = null

    var next: DLinkNode? = null

    constructor()

    constructor(_key: Int, _value: Int) {
        key = _key
        value = _value
    }
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
