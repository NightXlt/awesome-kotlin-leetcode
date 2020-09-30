# [打印从1到最大的n位数][title]

## Solution
全排列解决，先排好高位再排低位。
以 n = 2为例
```
00, 01, 02, ..10, 11, 12
```
因为要涉及字符串修改，采用 StringBuilder 进行字符串修改。 因为是从 1 开始遍历，所以需要去除前置 0，且当 str 不为空时才添加到 list 中
从i = 0到 9进行遍历，将i 追加到 str 中，继续深搜，这样高位就定下来了，（这时尽管首位出现了 0，但可以放到添加时处理前导 0）。继续深搜低位。
回溯时重置状态将最后一位字符去除掉。

```kotlin
class Solution {
    private var list: MutableList<Int> = mutableListOf()

    fun printNumbers(n: Int): IntArray? {
        if (n <= 0) return intArrayOf()
        dfs(n, 0, StringBuilder())
        return list.toIntArray()
    }

    fun dfs(n: Int, curIndex: Int, str: StringBuilder) {
        if (curIndex == n) {
            val res = str.trimStart('0')
            if (res.isNotEmpty()) {
                list.add(str.toString().toInt())
            }
            return
        }
        for (i in 0..9) {
            str.append(i)
            dfs(n, curIndex + 1, str)
            if (str.isNotEmpty()) {
                str.deleteCharAt(str.lastIndex)
            }
        }
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
