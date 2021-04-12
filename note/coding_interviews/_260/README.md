# [数组中只出现一次的数字][title]

## Solution
一个数组只有一个数字出现一次，就不多说了；这道题再做有了新的理解。 `分治思想`
首先计算得到数组所有元素的异或结果`xorRes`。 
计算得到 `xorRes` 只包含最右侧的1 对应的十进制。如 6（110）对应的是 `diff` 2（10）
那最右侧的 1 必然是来源于两个只出现一次的元素中的一个嘛(`xorRes` 是异或结果嘛)
遍历一遍数据元素，如果与 `diff` 进行与操作结果不为零。就对该元素进行异或操作。这样就将数据分为了两组，与上最右侧 1 结果不为 0 的一组，为 0 的一组。
那么记录 与上最右侧 1 结果不为 0 的一组的结果，得到其中一个唯一元素，异或上 `xorRes` 得到 另一个唯一元素

```kotlin
package com.blankj.coding_interviews._260

import com.blankj.coding_interviews._004.print

class Solution {
    fun singleNumber(nums: IntArray): IntArray {
        val res = IntArray(2)
        var xorRes = 0
        nums.forEach {
            xorRes = xorRes xor it
        }
        val diff = getLowestBits(xorRes)
        res[0] = 0
        nums.forEach {
            if (it and diff != 0) {
                res[0] = res[0] xor it
            }
        }
        res[1] = xorRes xor res[0]
        return res
    }

    // 获取数字只保留二进制 1 的一位。参考 Integer.lowestOneBit; 学习highestOneBit。 比如哈 6 的二进制是 110， -6 的二进制是 010. 计算得到010. 只保留数字最右侧的 0
    private fun getLowestBits(xorRes: Int): Int {
        return xorRes and -xorRes
    }
}

fun main() {
    Solution().singleNumber(intArrayOf(1, 2, 1, 3, 2, 5)).print()
}

```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/single-number-iii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
