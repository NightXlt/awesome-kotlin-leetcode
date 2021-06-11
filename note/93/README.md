# [Restore IP Addresses][title]

## Solution

套路的回溯模板题，
从头开始深搜，记录 start 指针，遍历到数组结束时，如果满足题解条件如Ip 地址已经有四段
每次深搜的选择，只会从合理的下标开始，比如一段IP长度的选择是 1..4. 那么深搜遍历的范围 start..start + 3. 全集的范围是到 length 结束
将合理的结果A添加到 「结果集」 中， 继续深搜
深搜结束（无论成功或失败），从结果集中队尾移除添加进的结果A, 重置状态

很多时候，回溯的难点在于剪枝，怎么剪枝尽可能降低时间复杂度，但又不至于漏解。


讲下里面的一些剪枝细节

- 如果数组长度不在 4..12中，必定是非法的ip
- 记录剩余段数 residual， 每次往 path 添加一个 string, residual - 1，如果到了最后 residual段数，每段拉满，但都撑不满剩余可用长度，说明剩下的字符太多了
比如
```text
1.1.1.2222
这明显是个不合规的 IP。当将前面三个1添加进 path, residual = 1，
可还剩下 len("2222222") = 7 > residual * 3 = 3。这样就可以在 len - 7时跳过 1.1.1这种情况，但不是通过 break 跳出，而是 continue. 
因为可以通过测试增加前一段的长度，从而减小后一段长度，这还是有可能找到符合可能的结果的。再一次回溯得到的结果
1.1.12.222这个就是合规的嘛
```
- 判断合规IP 时，留意前缀0 情况

- 避免字符串操作创建多余字符串，通过遍历字符串，手动得到当前段数数字

```kotlin
    fun restoreIpAddresses(s: String): List<String> {
        if (s.length !in 4..12) return emptyList()
        val res = mutableListOf<String>()
        val path: Deque<String> = ArrayDeque(4)
        dfs(s, s.length, 0, 4, res, path)
        return res
    }

    private fun dfs(
            s: String,
            length: Int,
            start: Int,
            residue: Int,
            res: MutableList<String>,
            path: Deque<String>
    ) {
        if (start == length) {
            if (residue == 0) {
                res.add(path.joinToString(separator = "."))
            }
            return
        }
        for (i in start..start + 3) {
            if (i >= length) break
            // Though residue is full, length cant be used up
            if (residue * 3 < length - i) continue
            if (isValidIP(s, start, i)) {
                val curIp = s.substring(start, i + 1)
                path.addLast(curIp)
                dfs(s, length, i + 1, residue - 1, res, path)
                path.removeLast()
            }
        }
    }

    private fun isValidIP(s: String, start: Int, end: Int): Boolean {
        val length = end - start + 1
        // leading zeros
        if (length > 1 && s[start] == '0') return false
        var res = 0
        var start = start
        // Avoid string op
        while (start <= end) {
            res = res * 10 + (s[start] - '0')
            start++
        }
        return res in 0..255
    }
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/restore-ip-addresses/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
