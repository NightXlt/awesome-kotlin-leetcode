#[圆圈中最后剩下的数字][title]

## Solution
动规规划题目

f(n, m)表示 n 个数字，按序依次去除 第m个数字，最后剩下的一个数字。即题目所求
数字为：0,1,···,n-1。
f(1, m) 当只有一个数字时，去除m次数字剩下都为 0.

f(n, m) 第一次去除的第 m 个数字为 (m - 1) % n 「因为 m 可能大于 n」。 令 k = (m - 1) % n
```

剩下的数字为 0，1，..., k-1, k+1, k+2, ..., n - 1。记为 g(n-1, m)
而 f(n - 1, m)的对应数字原始排列为
    0,   1,  ···,  n - k -2, n - k - 1, n-2
将g(n-1, m) 重排序下如 f(n-1, m)对齐
    k+1, k+2, ..., n-1,      0,         1,    2, ..., k-1
```
| f(n-1,m)                                    | g(n-1, m)                                      |
| :--------------------------------------- | :--------------------------------------- |
| 0                       | k + 1                        |
| 1                       | k + 2                        |
| n - k - 2               | n - 1                        |
| n - k - 1               | 0                            |
| n-2                     | k - 1                        |

可以观察得到 两个序列数字 存在递推规律，
(f(x) + k + 1) % m = g(x)
那么 g(n - 1, m) = (f(n - 1, m) + k + 1) % m; 因为 g(n - 1, m), f(n - 1, m)也是来自于f,g 序列中的一个数字嘛
因此得出递推关系 
```text
f(n, m) = g(n - 1, m) = (f(n−1)+t)%n
       =(f(n−1) + m % n) % n                    
       =(f(n−1) + m) % n  (n > 1)
f(1, m) = 0  (n == 1)
```
对应到代码的小细节时这里的 取余 n 是会变的， 从 2..n。
```kotlin
class Solution {
     
    fun lastRemaining(n: Int, m: Int): Int {
        if (n < 1 || m < 1) return -1
        var result = 0
        for (i in 2..n) {
            // 取余当前的序列长度
            result = (result + m) % i
        }
        return result
    }

}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
