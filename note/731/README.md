# [My Calendar II][title]

## Solution
通过 TreeMap 记录开始和结束点的 count， 因为 TreeMap 支持排序。
通过遍历 map 中的 values 就可以知道区间中是否存在超过三段的情况
比如
```
(1,9), (2,8), (3,7)
首先 1，9 分别加到 map 中
1： 1， 9: -1
2: 1  8: -1
当添加 3， 7 后
我们累加 map values 可以发现存在 和 > 2， 这意味着出现 3段重叠了， 返回 false 
```

```kotlin
class MyCalendarTwo() {

    val map = TreeMap<Int, Int>().withDefault { 0 }

    fun book(start: Int, end: Int): Boolean {
        map[start] = map.getValue(start) + 1
        map[end] = map.getValue(end) - 1
        var activeBookings = 0
        for (ending in map.values) {
            activeBookings += ending
            if (activeBookings > 2) {
                map[start] = map.getValue(start) - 1
                map[end] = map.getValue(end) + 1
                return false
            }
        }
        return true
    }

}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/find-the-duplicate-number/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
