# [替换空格][title]

## Solution
本题的坑点是在 C++情况下输入是 char 数组，如果硬来解决， 类似双重循环的搞复杂度是 O(n^2). 向面试官确认两点。
1. 是直接在原字符串上进行修改，这样可能覆盖后面的字符串。需要保证后面有足够的空间
2. 新起字符串。两种解法都是新起字符串。因为输入是 String。如果是 char 数组可以这么搞
 
第一种解法是模拟书上，写的很别扭。因为 java、kotlin 很少情况下会去操作 char 这个数组。后面用了 StringBuilder改造了下。
注意 StringBuilder 执行效率优于 StringBuffer， 因为 builder 不是线程安全的，而 StringBuffer 是线程安全的


如果输入是 char 数组也可以搞，需要确保
1.末尾有多余空间情况下
2. 老数组以\0结尾
先遍历一遍老数组，得到空格数目 spaceCount, 替换后的数组长度newLen = 老数组长度 + spaceCount * 2.
一个指针指向老数组的 lastIndex , 一个指针指向 newLen. 遇到空格， newLen -= 3, 将 %20复制进去。否则将原数组元素复制进去

测试用例：
空串
仅有一个空格
只有多个空格
单词间只有一个空格
单词间有多个空格
没有空格
```kotlin
class Solution {
    fun replaceSpace(s: String): String {
        if (s.isEmpty()) return s
        val spaceCount = s.count { it == ' ' }
        val newLen = s.length + spaceCount * 2
        val char = CharArray(newLen)
        var lastIndex = newLen - 1
        for (index in s.lastIndex downTo 0) {
            if (s[index] == ' ') {
                char[lastIndex--] = '0'
                char[lastIndex--] = '2'
                char[lastIndex--] = '%'
            } else {
                char[lastIndex--] = s[index]
            }
        }
        return String(char, 0, newLen)
    }

    fun replaceSpaceSB(s: String): String {
        if (s.isEmpty()) return s
        val stringBuilder = StringBuilder()
        s.forEach {
            if (it == ' ') {
                stringBuilder.append("%20")
            } else {
                stringBuilder.append(it)
            }
        }
        return stringBuilder.toString()
    }
}

fun main() {
    // test cases
    Solution().replaceSpaceSB("").print() // one space
    Solution().replaceSpaceSB(" ").print() // one space
    Solution().replaceSpaceSB("We are happy.").print() // single space
    Solution().replaceSpaceSB("We are  happy.").print() // double space
    Solution().replaceSpaceSB("Wearehappy.").print() // no space
}
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
