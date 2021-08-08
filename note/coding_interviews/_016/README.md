# [数值的整数次方][title]

## Solution
这道题自己依稀记得是 ACM 课上讲过的快速幂，做的时候自己下意思会漏了指数为负数的情况。
指数为负数时将其转换为 1/base。

迭代法：i % 2 != 0这个条件不能换成 i % 2 == 1 因为 i 可能为负数 -1 % 2 == -1的，这样会导致结果有问题。
递归法：当 n 为Int.MIN_VALUE， 变成正数时会溢出，所以先将其提出一个 1 / base使得-n 变成-n-1 这样就不会越界了
```kotlin
class Solution {
    fun myPow(x: Double, n: Int): Double {
        var res = 1.0
        var i = n
        var x = x
        // double cant use == op comparison simply
        if (abs(x) < 0.00000001 && n < 0) throw IllegalArgumentException("base is 0, but exponent is less than 0")
        while (i != 0) {
            if (i % 2 != 0) {
                res *= x
            }
            i /= 2
            x *= x
        }
        return if (n < 0) 1.0 / res else res
    }

    fun myPow1(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        // double cant use == op comparison simply
        if (abs(x) < 0.00000001 && n < 0) throw IllegalArgumentException("base is 0, but exponent is less than 0")
        //如果n小于0，把它改为正数，并且把1/x提取出来一个,避免出现-Int.MIN_VALUE 越界
        if (n < 0) return 1 / x * myPow(1 / x, -n - 1)
        return if (n % 2 == 0) myPow(x * x, n / 2) else x * myPow(x * x, n / 2)
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
