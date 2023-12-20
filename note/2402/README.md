# [Meeting Rooms III][title]

## Solution
为了方便处理，首先将数组 meetings 按会议的开始时间升序排序，然后按时间顺序模拟分配会议室的操作，计算每个会议室举办会议的数量。

根据分配会议室的规则，需要维护两类信息。

1. 未占用的会议室编号，需要支持获得编号最小的未占用的会议室。

2. 进行中的会议，包含会议的结束时间和占用的会议室编号，需要支持获得结束时间最早且会议室编号最小的会议。

因此需要维护两个优先队列，分别是会议室优先队列和会议优先队列。

会议室优先队列包含未占用的会议室编号，每个元素是会议室编号，队首元素是编号最小的未占用的会议室。

会议优先队列包含进行中的会议，每个元素是会议结束时间和占用会议室编号的二元组，队首元素是结束时间最早且会议室编号最小的会议。

由于初始时没有会议，因此将 0 到 n−1 的所有会议室编号加入优先队列。

遍历排序后的数组 meetings，遍历过程中维护两个优先队列的信息，并使用哈希表记录每个会议室举办会议的数量。当遍历到会议 [start,end] 时，执行如下操作。

计算会议持续时间 duration=end−start，用于当会议延期时计算新的结束时间。

如果会议优先队列不为空且队首元素的结束时间小于等于 start，则会议优先队列的队首元素对应的会议已经结束，将会议优先队列的队首元素取出，并将该元素中的会议室下标加入会议室优先队列。重复该操作直到会议优先队列为空或队首元素的结束时间大于 start。

如果会议室优先队列为空，则没有可用的会议室，需要将当前会议的开始时间延期到进行中的会议的最早结束时间，因此执行如下操作。

    将会议优先队列的队首元素取出，得到该会议的结束时间 finishTime 和会议室下标 finishRoomIndex。

    当前会议的开始时间延期到 finishTime，会议持续时间不变，因此将 start 的值更新为 finishTime，将 end 的值更新为 start+duration。

    将 finishRoomIndex 加入会议室优先队列。

从会议室优先队列中取出编号最小的未占用的会议室 roomIndex，将编号为 roomIndex 的会议室分配给当前会议。因此将编号为 roomIndex 的会议室举办会议的数量加 1，将二元组 [end,roomIndex] 加入会议优先队列。

遍历结束之后，即可得到每个会议被分配的会议室与每个会议室举办的会议数量，遍历所有会议室举办的会议数量即可得到举办最多会议的会议室编号。

```kotlin
import java.util.*

class MostBooked_2402 {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        val counts = IntArray(n)
        // 获取最小的可用会议室
        val available = PriorityQueue<Int>()
        repeat(n) { available.add(it) }
        // 获取最早结束时间和会议室
        val finishTimes = PriorityQueue<TimeIndexPair> { o1, o2 ->
            return@PriorityQueue if (o1?.time != o2?.time) {
                (o1?.time.orEmpty()) - (o2?.time.orEmpty())
            } else {
                o1?.index.orEmpty() - o2?.index.orEmpty()
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

    private fun Int?.orEmpty(): Int {
        return this ?: 0
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
