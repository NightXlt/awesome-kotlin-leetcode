package com.blankj.medium._253

import java.util.*

class Solution {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        // incremental queue
        val q = PriorityQueue<Int> { a, b -> a - b }
        // sorted by start time
        Arrays.sort(intervals) { l, r -> l[0] - r[0] }
        var res = 0
        for (i in intervals) {
            // reuse last meeting room
            while (!q.isEmpty() && q.peek() <= i[0]) {
                q.poll()
            }
            q.add(i[1])
            // record max meeting room count
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