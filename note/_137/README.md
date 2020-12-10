# [只出现一次的数字 II][title]

## Solution
只有一个数字U出现过一次，其他数字出现出现三次。而且数字是 Int 类型。Int 的二进制只有三十二位。
看个栗子

```
intArray(2, 2, 3, 2)
二进制表示
2: 10
3: 11

判断第 0 位，2 的第 0 位是 0，3 的第 0 位是 1. 累计结果为 1. 1%3 == 1. 表明唯一出现的数的第 0 位为 1. res |= 1 << 0, res = 1
判断第 1 位，2 的第 1 位是 1，3 的第 1 位是 1. 累计结果为 4. 4%3 == 1. 表明唯一出现的数的第 1 位为 1. res |= 1 << 1, res = 3
最后因为 2..31位都是 0，0%3 == 0，就不进行或操作了。
得到的结果 res=3. 数组中唯一元素就是 3.
```
遍历Int 二进制的每一位i(32次)
    统计数组每个数在 i 位上出现次数。
    将统计的出现次数%3，因为其他数字出现次数为 3 次，如果唯一数字U的i位上不为 1，那%3的结果会为 0，
    反过来如果%3的结果为 1，，就表明U的第 i 位为 1.那么累计或上 1<<i 位得到的结果就是 U 啦。
```kotlin
class SingleNumberII {
    fun singleNumber(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty()) {
            return Int.MIN_VALUE
        }
        var result = 0
        for (i in 0..31) {
            var sum = 0
            for (num in nums) {
                if (num shr i and 1 == 1) {
                    sum++
                }
            }
            if (sum % 3 != 0) {
                result = result or (1 shl i)
            }
        }
        return result
    }
}

fun main() {
    SingleNumberII().singleNumber(intArrayOf(2, 2, 3, 2)).print()
    SingleNumberII().singleNumber(intArrayOf(0, 1, 0, 1, 0, 1, 99)).print()
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/single-number-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
