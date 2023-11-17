package com.blankj.medium._133

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}

class Solution {

    private val visited = hashMapOf<Int, Node>()

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null

        if (visited.containsKey(node.`val`)) {
            return visited[node.`val`]
        }
        val newNode = Node(node.`val`)
        visited[node.`val`] = newNode
        for (neighbor in node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor))
        }
        return newNode
    }
}
