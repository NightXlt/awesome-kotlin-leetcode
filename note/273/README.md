# [Integer to English Words][title]

## Solution

这道题的思路是分治思想，将原数组拆成三个一组的子数字，并记录其分隔符下标，"", Thousand, Million, Billion.
比如 123456789，
123 456 789

先统计 789，得到 Seven hundred eighty night
继续 457 得到 four hundred fifty seven, 再加上累计的分隔符 Thousand 得到 four hundred fifty seven thousand
继续 123 得到 one hundred twenty three, 再加上累计的分隔符 Million 得到 one hundred twenty three million
 
思路还是很清晰，但细节会比较多。
1. 空格, 最后得到的结果末尾可能会有空格，比如90。得到 ninety  两个空格，需要去除掉
2. 数字为0时有两种情况，
- num 整体为 0， 或者是其中某三位为 0，区别处理，
- 某三位为零时，不做处理，更新分隔符

```kotlin
class Solution {

    companion object {
        private val lessThanTwenty = arrayOf(
                "", "One", "Two", "Three",
                "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven",
                "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen")

        private val Tens = arrayOf(
                "", "", "Twenty", "Thirty",
                "Forty", "Fifty", "Sixty",
                "Seventy", "Eighty", "Ninety")

        private val SEPARATOR = arrayOf(
                "",
                "Thousand",
                "Million",
                "Billion"
        )
    }

    fun numberToWords(num: Int): String {
        if (num == 0) return "Zero"
        if (num < 20) return lessThanTwenty[num]
        var num = num
        var i = 0
        val result: StringBuilder = StringBuilder()
        while (num > 0) {
            val t = num % 1000
            if (t != 0) {
                result.insert(0, getNumToString(t) + SEPARATOR[i] + " ")
            }
            i++
            num /= 1000
        }
        return result.trim().toString()
    }

    private fun getNumToString(num: Int): String {
        return when {
            // zero branch match required; such as 90, return ninety " "， or else return ninety "  " (Two blanks
            num == 0 -> ""
            num < 20 -> lessThanTwenty[num] + " "
            num < 100 -> {
                Tens[num / 10] + " " + getNumToString(num % 10)
            }
            else -> {
                lessThanTwenty[num / 100] + " Hundred " + getNumToString(num % 100)
            }
        }
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/lru-cache/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
