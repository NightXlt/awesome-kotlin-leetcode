# [Strobogrammatic Number II][title]


## Solution

长度为 0 的中心对称数不存在，因此返回值包含一个空字符串。长度为 1 的中心对称数有：0、1、8。

当 n ≥ 2 时，长度为 n 的中心对称数可以由长度为 n - 2 的中心对称数生成，因此可以通过递归的方式得到所有长度为 n 的中心对称数。

虽然在最终结果中，除了数字 0 以外不能有任何中心对称数的最高位是 0，但是由于其余任何位都可以是 0，因此在生成所有长度为 n
的中心对称数时，必须同时考虑最高位是 0 的中心对称数。
当 n 是偶数时，从空字符串开始生成长度为 n 的中心对称数。当 n 是奇数时，从 0、1、8 开始生成长度为 n 的中心对称数。从长度为 n - 2 的中心对称数生成长度为 n 的中心对称数的方法是：在长度为 n - 2 的中心对称数的左右分别加上以下 5 组数：0 和 0、1 和 1、6 和 9、8 和 8、9 和 6。例如，1 是长度为 1 的中心对称数，从 1 生成的长度为 3 的中心对称数有：010、111、619、818、916。该方法可以通过递归的方式实现。

当生成包括最高位为 0 在内的所有长度为 n 的中心对称数之后，去掉其中最高位为 0 的中心对称数（除了数字 0 以外），剩下的数就是符合要求的所有长度为 n 的中心对称数。

```kotlin
class Solution {
    fun findStrobogrammatic(n: Int): List<String> {
        if (n < 1) return emptyList()
        val res = findStrobogrammaticWithPrefixZero(n)
        return res.filterNot {
            it.length > 1 && it.first() == '0'
        }
    }

    private fun findStrobogrammaticWithPrefixZero(n: Int): MutableList<String> {
        val queue = ArrayDeque<String>()
        val start = n % 2
        // 确定首位从哪里开始
        if (start == 0) {
            queue.add("")
        } else {
            queue.addAll(listOf("0", "1", "8"))
        }
        for (i in start..<n step 2) {
            val size = queue.size
            repeat(size) {
                val prevStrobogrammatic = queue.removeFirst()
                queue.add("0" + prevStrobogrammatic + "0")
                queue.add("1" + prevStrobogrammatic + "1")
                queue.add("6" + prevStrobogrammatic + "9")
                queue.add("8" + prevStrobogrammatic + "8")
                queue.add("9" + prevStrobogrammatic + "6")
            }
        }
        return queue.toMutableList()
    }
}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]


[title]: https://leetcode-cn.com/problems/meeting-rooms-ii/

[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
