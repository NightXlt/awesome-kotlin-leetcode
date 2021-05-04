# [Number of Islands][title]
给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。

```text
输入：intervals = [[0,30],[5,10],[15,20]]
输出：2

输入：intervals = [[7,10],[2,4]]
输出：1
```

## Solution
贪心思想
尽可能少的预订会议室。
以开始时间为维度对数组排序，通过一个小顶堆下需要预定的会议室的结束时间，
遍历数组的每个interval发现interval 的开始时间如果比队首的结束时间要大，

说明这个interval的开始时间和结束时间都是位于队首元素会议时间后面的，因为在数组一开始被按照开始时间排过序，
 和当前的队首的会议室可以共用，更新该会议室的结束时间； 统计小顶堆中元素个数即为最小需要预定的会议室

```kotlin
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

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/meeting-rooms-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
