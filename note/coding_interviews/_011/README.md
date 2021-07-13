# [旋转数组的最小数字][title]

## Solution
如果是顺序情况，返回最小的元素即可

二分查找最小值时，数组有一个特性可以利用： 数组是递增的

那么就可以二分查找了
当最小值比 high 指针元素大时，最小值必然在m 指针的右侧区域元素
当最小值比 low 指针元素小时，最小值必然在m 指针的左侧区域元素(包含自己)
可当最小值比 high 指针元素相等时，最小值不能确定是在m 的那篇区域，只能逐步试探 high--

```kotlin
    fun minArray(numbers: IntArray): Int {
        var low = 0
        var high = numbers.size - 1
        if (numbers[low] < numbers[high]) return numbers[low]
        while (low < high) {
            val m = low + ((high - low) shr 1)
            when {
                numbers[m] > numbers[high] -> low = m + 1
                numbers[m] < numbers[low] -> high = m
                else -> high--
            }
        }
        return numbers[low]
    }
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
