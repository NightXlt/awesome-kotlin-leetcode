# [Race Car][title]

## Solution
求最短指令长度, 每一步有两个选项, 但不要求求出所有路径, 其实应该用 dp 做, 但短时间没想出 dp, 就用 bfs 来解决了
队列中记录当前的 position 和 speed, 每一步尝试将这层节点的合法相邻节点加到队列中. 至于为啥要取 newPosition < target * 2 判断, 我不知道.
实测换成 1.5 也是可以的, 猜测是因为赛车位置可能超过 target, 然后需要拐回来, 但如果超过太多的话, 这个解就可以舍掉了, 

因为还有可能在临近 target 进行两次 R, 倒车后再转回来正向加速 

visited 记录 postion 和 speed 的快照, 防止重复访问

```kotlin

class Solution {
    fun racecar(target: Int): Int {
        if (target <= 0) return 0
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        queue.add(0 to 1)
        val visited = mutableSetOf("0#1")
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (position, speed) = queue.removeFirst()
                if (position == target) return res
                var newPosition = position + speed
                var newSpeed = speed * 2
                var flag = "$newPosition#$newSpeed"
                if (flag !in visited && newPosition > 0 && newPosition < target * 2) {
                    visited.add(flag)
                    queue.add(newPosition to newSpeed)
                }
                newPosition = position
                newSpeed = if (speed > 0) -1 else 1
                flag = "$newPosition#$newSpeed"
                if (flag !in visited && newPosition > 0 && newPosition < target * 2) {
                    visited.add(flag)
                    queue.add(newPosition to newSpeed)
                }
            }
            res++
        }
        return -1
    }

}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/race-car/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
