package com.blankj.medium._253

import java.util.*

class Solution {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        val q = PriorityQueue<Int> { a, b -> a - b }
        Arrays.sort(intervals) { l, r -> l[0] - r[0] }
        var res = 0
        for (i in intervals) {
            while (!q.isEmpty() && q.peek() <= i[0]) {
                q.poll()
            }
            q.add(i[1])
            if (q.size > res) {
                res = q.size
            }
        }
        return res
    }
}

fun main() {
    Solution().minMeetingRooms(arrayOf(
            intArrayOf(0,30),
            intArrayOf(5,10),
            intArrayOf(15,20),
            intArrayOf(25, 40)
    ))
}