# [Basic Calculator][title]

## Solution
用一个辅助栈记录`(`之前的符号以及sum和。 sign 记录当前元素的符号
- 每遇到一个`(`, 就将 res 以及`(`前符号入栈，栈顶是符号。重置 res 为 0，sign 为 1. 
- 遇到`)`, 出栈两个元素。先将 `()` 计算得到结果res * 符号 + 前序的计算结果
- 字符为数字，遍历数组得到当前操作数；小细节，累加数字是 num `=` 10 * num + (s\[i\] - '0')；而非num `+=` 10 * num + (s\[i\] - '0')；

遍历结束即得所求

举个例子 3-(1 + 2)
```
1. s[i] = 3   res = 3 , sign = 1, stack=[]
2. s[i] = -   res = 3 , sign = -1, stack=[]
3. s[i] = (   res = 0 , sign = 1, stack=[3, -1]
4. s[i] = 1   res = 1 , sign = 1, stack=[3, -1]
5. s[i] = +   res = 1 , sign = 1, stack=[3, -1]
6. s[i] = 2   res = 3 , sign = 1, stack=[3, -1]
7. s[i] = )   res = 3 * -1(符号) + 3(左括号前面的累计和) = 0 , sign = 1, stack=[]
```

这里入栈两个元素的行为很巧妙，有些类似 Class结构排布的规则。

```kotlin
class Solution {
    fun calculate(s: String?): Int {
        if (s.isNullOrBlank()) return 0
        val stack = ArrayDeque<Int>()
        var i = 0
        var sign = 1
        var res = 0
        val digitRange = '0'..'9'
        while (i < s.length) {
            when (s[i]) {
                '+' -> {
                    sign = 1
                    i++
                }
                '-' -> {
                    sign = -1
                    i++
                }
                '(' -> {
                    stack.push(res)
                    stack.push(sign)
                    res = 0
                    sign = 1
                    i++
                }
                ')' -> {
                    res = stack.pop() * res + stack.pop()
                    i++
                }
                in digitRange -> {
                    var num = 0
                    while (i < s.length && s[i] in digitRange) {
                        num = 10 * num + (s[i] - '0')
                        i++
                    }
                    res += sign * num
                }
                else -> i++
            }
        }
        return res
    }

}
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/basic-calculator/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
