# [Employee Free Time][title]

## Solution
如果某个区间与任一区间重叠，则该区间不会出现在答案中。所以我们可以将问题转换为：给定一组区间，找出所有员工都不包含的区间。

我们可以使用区间问题中的 “事件” 方法。对于每个区间 [s, e]，我们可以看作有两个事件：
当 time = s 时，balance--；
当 time = e 时，balance++。我们只关心 balance == 0 的区间。


算法：
指定 start 为 -1， end 为 1 有利于 start 排在 end 前面。 balance-- 和 ++ 的逻辑也和这个有关系

对于每个区间，创建如上所述的两个事件，并对事件进行排序。在事件 t 发生的每个事件，如果 balance == 0，则说明 [prev，t] 是所有员工都不包含的区间，其中 prev 是 t 的前一个值。



```kotlin
/*
 *	// Definition for an Interval.
 *	class Interval {
 *		var start:Int = 0
 *		var end:Int = 0
 *	
 *		constructor(_start:Int, _end:Int) {
 *			start = _start
 *			end = _end
 *		}
 *	}
 */

class Solution {
    fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): ArrayList<Interval> {
        val events = mutableListOf<IntArray>()
        val open = -1
        val closed = 1
        schedule.flatten().forEach {
            events.add(intArrayOf(it.start, open))
            events.add(intArrayOf(it.end, closed))
        }
        events.sortWith(compareBy({ it[0] }, { it[1] }))
        var res = arrayListOf<Interval>()
        var prev = -1
        var balanced = 0
        for (event in events) {
            if (balanced == 0 && prev >= 0) {
                res.add(Interval(prev, event[0]))
            }
            balanced += event[1]
            prev = event[0]
        }
        return res
    }

}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/employee-free-time/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
