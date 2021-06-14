# [Basic Calculator II][title]

## Solution
这道题没有 `()`，可以不用将符号和数字分开存储，可以存储数字时将符号带上；

但因为乘除的存在，需要先遍历一遍字符串，先处理乘除的结果存储到栈中。在对栈中的元素进行加减法操作即可
举个例子 3-2*2
```
1. s[i] = 3   num = 3 , preSign = 1
2. s[i] = -   num = 0 , preSign = -1  stack=[3]
3. s[i] = 2   num = 2 , preSign = -1  stack=[3]
4. s[i] = *   num = 0 , preSign = *  stack=[3, -1 * 2 = -2]
5. s[i] = 2   num = 2 , preSign = *  stack=[3, -2 * 2 = -4]
6. 累加栈中所有元素得到结果
```

```kotlin
    fun calculate(s: String?): Int {
        if (s.isNullOrBlank()) return 0
        var num = 0
        var preSign = '+'
        val digitRange = '0'..'9'
        val stack = ArrayDeque<Int>()
        for (i in s.indices) {
            // Num: acc number 
            if (s[i] in digitRange) {
                num = num * 10 + (s[i] - '0')
            }
            // Symbol / last element: handle previous operation
            if (s[i] !in digitRange && s[i] != ' ' || i == s.lastIndex) {
                when (preSign) {
                    '+' -> {
                        stack.push(num)
                    }
                    '-' -> {
                        stack.push(-num)
                    }
                    '*' -> {
                        stack.push(stack.pop() * num)
                    }
                    '/' -> {
                        stack.push(stack.pop() / num)
                    }
                }
                preSign = s[i]
                num = 0
            }
        }
        return stack.fold(0) { acc, i ->
            acc + i
        }
    }

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/basic-calculator-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
