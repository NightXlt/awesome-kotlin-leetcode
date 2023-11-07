package com.blankj.hard._1494

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import java.util.*

class Solution {
    fun minNumberOfSemesters(n: Int, relations: Array<IntArray>, k: Int): Int {
        val d = IntArray(n + 1)
        for (edge in relations) {
            d[edge[1]] = d[edge[1]] or (1 shl edge[0])
        }
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(0, 0))
        val visited = mutableSetOf<Int>()
        visited.add(0)
        while (queue.isNotEmpty()) {
            // 状态变量 cur 表示当前已经上过的课程的集合
            val (cur, semester) = queue.removeFirst()
            if (cur == (1 shl (n + 1)) - 2) {
                return semester
            }
            var next = 0
            for (i in 1..n) {
                // 如果当前上过的课程中包含 i 的所有先修课程的情况下， 加入 i
                if (cur and d[i] == d[i]) {
                    next = next or (1 shl i)
                }
            }
            // 再次剔除掉之前上过的课， 判断这个学期上过的课有没有超限呀
            next = next xor cur
            if (Integer.bitCount(next) <= k) {
                // 剪枝移除掉重复访问的课程
                if (visited.add(next or cur)) {
                    queue.add(intArrayOf(next or cur, semester + 1))
                }
            } else {
                val x = next
                while (next > 0) {
                    if (Integer.bitCount(next) == k && visited.add(next or cur)) {
                        queue.add(intArrayOf(next or cur, semester + 1))
                    }
                    next = (next - 1) and x
                }
            }
        }
        return 0
    }
}

fun main() {
    Solution().minNumberOfSemesters(
        4,
        MultiDimensionArray.createTestData("[[2,1],[3,1],[1,4]]"),
        2
    ).print()
    Solution().minNumberOfSemesters(
        5,
        MultiDimensionArray.createTestData("[[2,1],[3,1],[4,1],[1,5]]"),
        2
    ).print()
    Solution().minNumberOfSemesters(
        11,
        emptyArray(),
        2
    ).print()
}