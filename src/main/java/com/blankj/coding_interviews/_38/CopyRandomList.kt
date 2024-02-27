package com.blankj.coding_interviews._38


class CopyRandomList {
//    fun copyRandomList(head: Node?): Node? {
//        if (head == null) return null
//        var cur = head
//        val map = mutableMapOf<Node, Node>()
//        while (cur != null) {
//            map[cur] = Node(cur.`val`)
//            cur = cur.next
//        }
//        cur = head
//        while (cur != null) {
//            map[cur]!!.next = map[cur.next]
//            map[cur]!!.random = map[cur.random]
//            cur = cur.next
//        }
//        return map[head]
//    }

    fun copyRandomList(head: Node?): Node? {
        if (head == null) return null
        cloneNodes(head)
        connectSiblingNodes(head)
        return reconnectNextNode(head)
    }

    private fun reconnectNextNode(head: Node): Node? {
        var cur: Node? = head
        var cloneHead: Node? = null
        var cloneNode: Node? = null
        if (cur != null) {
            cloneNode = cur.next
            cloneHead = cloneNode
            cur.next = cloneHead!!.next
            cur = cur.next
        }
        while (cur != null) {
            cloneNode!!.next = cur.next
            cloneNode = cloneNode!!.next
            cur.next = cloneNode!!.next
            cur = cur.next
        }
        return cloneHead
    }

    private fun connectSiblingNodes(head: Node) {
        var cur: Node? = head
        while (cur != null) {
            val cloneNode = cur.next
            val sibling = cur.random
            if (sibling != null) {
                cloneNode!!.random = sibling.next
            }
            cur = cloneNode!!.next
        }
    }

    private fun cloneNodes(head: Node) {
        var cur: Node? = head
        while (cur != null) {
            val next = cur.next
            val cloneNode = Node(cur.`val`)
            cur.next = cloneNode
            cloneNode.next = next
            cur = next
        }
    }
}