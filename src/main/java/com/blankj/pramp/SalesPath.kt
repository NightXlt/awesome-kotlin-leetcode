package com.blankj.pramp

class SalesPath {
    class Node(val cost: Int) {
        var children: Array<Node>? = null


    }
    fun getCheapestCost(rootNode: Node): Int {
        if (rootNode.children == null) {
            return rootNode.cost
        }

        var minCost = Int.MAX_VALUE
        var cost: Int
        for (n in rootNode.children!!) {
            cost = getCheapestCost(n)
            if (cost < minCost) {
                minCost = cost
            }
        }

        return minCost + rootNode.cost
    }
}