package com.blankj.google


import java.util.*

// 2402
class MostBooked_2402 {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        val counts = IntArray(n)
        val available = PriorityQueue<Int>()
        repeat(n) { available.add(it) }
        val finishTimes = PriorityQueue<TimeIndexPair> { o1, o2 ->
            return@PriorityQueue if (o1.time != o2.time) {
                o1.time - o2.time
            } else {
                o1.index - o2.index
            }
        }
        for (meeting in meetings) {
            var (start, end) = meeting
            val duration = end - start
            // remove minimum element in the heap if it's less than start
            while (finishTimes.isNotEmpty() && finishTimes.peek().time <= start) {
                available.add(finishTimes.remove().index)
            }
            if (available.isEmpty()) {
                val (firstFinishTime, firstFinishIndex) = finishTimes.poll()
                start = firstFinishTime
                end = start + duration
                available.add(firstFinishIndex)
            }
            val roomIndex = available.remove()
            counts[roomIndex]++
            finishTimes.add(TimeIndexPair(end, roomIndex))
        }
        return counts.indices.maxBy { counts[it] }
    }

    data class TimeIndexPair(
        val time: Int,
        val index: Int
    )
}
