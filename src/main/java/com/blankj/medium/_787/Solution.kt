package com.blankj.medium._787

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    /**
     *  bfs + res(temp array) stores CheapestPrice of (src to cur)
     */
    fun findCheapestPrice(
        n: Int,
        flights: Array<IntArray>,
        src: Int,
        dst: Int,
        k: Int
    ): Int {
        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        repeat(n) { graph[it] = mutableListOf() }
        for (flight in flights) {
            graph[flight[0]]!!.add(intArrayOf(flight[1], flight[2]))
        }
        val res = IntArray(n) { Int.MAX_VALUE }
        res[src] = 0
        var interchangeLimit = k + 1
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(src, 0))
        while (interchangeLimit > 0 && queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (cur, price) = queue.removeFirst()
                for (next in graph[cur]!!) {
                    val totalPrice = price + next[1]
                    if (totalPrice < res[next[0]]) {
                        queue.add(intArrayOf(next[0], totalPrice))
                        res[next[0]] = totalPrice
                    }
                }
            }
            interchangeLimit--
        }
        return if (res[dst] == Int.MAX_VALUE) -1 else res[dst]
    }
}

fun main() {
    Solution().findCheapestPrice(3, MultiDimensionArray.createTestData("[[0,1,100],[1,2,100],[0,2,500]]"),0,2,1).print()
    Solution().findCheapestPrice(3, MultiDimensionArray.createTestData("[[0,1,100],[1,2,100],[0,2,500]]"),0,2,0).print()

}