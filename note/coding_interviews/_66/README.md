#[构建乘积数组][title]

## Solution
动态规划进行递推 + 双次遍历的思想。一次统计 i 元素左侧的乘积，一次统计 i 元素右侧的乘积
dp\[i\] = dp\[i - 1\] * a\[i - 1\] 第一次遍历
dp\[i\] = dp\[i + 1\] * a\[i + 1\] 第二次遍历

为避免特殊处理，利用辅助变量实现 O(1)

```kotlin
class Solution {
     
    fun constructArr(a: IntArray?): IntArray {
        if (a == null || a.isEmpty()) return intArrayOf()
        var result = IntArray(a.size)
        var leftMultiplyResult = 1
        for (i in a.indices) {
            result[i] = leftMultiplyResult
            leftMultiplyResult *= a[i]
        }
        var rightMultiplyResult = 1
        for (i in a.indices.reversed()) {
            result[i] *= rightMultiplyResult
            rightMultiplyResult *= a[i]
        }
        return result
    }

}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
