# [Find Peak Element][title]

## Solution
和 852 找下标类似，但解法并不一样，在那道题中峰值可能是不存在的，但在本题中峰值是一定存在的。 
推导可以参考这个[题解](https://leetcode.cn/problems/find-peak-element/solutions/998441/gong-shui-san-xie-noxiang-xin-ke-xue-xi-qva7v/) 

我有点硬套模板了，这道题没有保证元素大于三个， 所以每个值都有可能是峰值， left = 0, right = n - 1 开始。
每次找到 mid > mid + 1 时，我可能找到了右边界， 更新 right = mid，
后续的二分过程会验证 mid - 1 > mid, 如果不大于的话，那么这个 right 实际就是最终要求的结果。

而如果是 <= 的情况下，必定不是峰值，更新左边界为 mid + 1.

直到左右边界不等时，退出循环，这时的 right 即为所求。
```kotlin
import kotlin.math.min
fun findPeakElement(arr: IntArray): Int {
    var left = 0
    var right = arr.lastIndex
    while (left < right) {
        val mid = (left + right) shr 1
        if (arr[mid] > arr[mid + 1]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return right
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/find-peak-element/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
