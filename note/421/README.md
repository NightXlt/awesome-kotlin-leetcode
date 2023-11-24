# [Maximum XOR of Two Numbers in an Array][title]

## Solution
两种解法， 第一种是我三年前记录的题解（https://nightxlt.github.io/2020/02/09/MAX_XOR/）
另一种就是前缀树了。

前一种自己都忘了，再回顾下，本质是基于贪心的思想，优先确定出高位的 1， 再依次定下低位
其次是利用 xor 特性， a ^ b = c, 那么 b ^ c = a, a ^ c = b 也成立。

后一种前缀树实测会超时，目测是数据量过大时， O(32n) 如果变成了 O(64n) 就会超时了，因为相比而言
后一种会需要一步 O(32n) 来构建前缀树。
结合贪心和前缀树特性比较出每个 num 和其他数字的最大异或结果, 然后求最大值即是结果

```kotlin
class Trie {

    var children = HashMap<Int, Trie>()
        private set

    /** Inserts a word into the trie. */
    fun insert(nums: IntArray) {
        for (num in nums) {
            var cur = this
            for (i in 31 downTo 0) {
                val bit = (num shr i) and 1
                cur = cur.children.getOrPut(bit) { Trie() }
            }
        }
    }
}

class Solution {
    fun findMaximumXOR(nums: IntArray): Int {
        var mask = 0
        var res = 0
        for (i in 31 downTo 0) {
            mask = mask or (1 shl i)
            val set = HashSet<Int>()
            for (num in nums) {
                set.add(num and mask)
            }
            val t = res or (1 shl i)
            for (prefix in set) {
                if (set.contains(prefix xor t)) {
                    res = t
                }
            }
        }
        return res
    }

    fun findMaximumXORWithTrie(nums: IntArray): Int {
        val trie = Trie()
        trie.insert(nums)
        var max = 0
        for (num in nums) {
            var cur = trie
            var xor = 0
            // 贪心的匹配高位的 1
            for (i in 31 downTo 0) {
                val bit = (num shr i) and 1
                // 如果存在当前 bit 的异或结果, 使当前位为 1
                if (1 - bit in cur.children) {
                    xor = (xor shl 1) + 1
                    cur = cur.children[1 - bit]!!
                } else {
                    xor = xor shl 1
                    cur = cur.children[bit]!!
                }
            }
            max = max(max, xor)
        }
        return max
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
