# [Odd Even Jump][title]

## Solution
题目就很难懂， 一句话总结就是， 奇偶换着跳， 奇数跳只能跳比他大的数字中最小的， 偶数跳只能跳比他小的数字中最大的。
其实到这里已经很绕了。参考了官方题解的第二种方法写了下。想明白了这个事， 从结果去推， 最后一个元素肯定能够跳到末尾，
我们分别用奇偶两个数组逆向来模拟跳跃过程， 此外通过 TreeMap 快速定位当前元素后面的元素中比他大，小的元素。

此外在发现 map 中存在当前元素时， 意味着当前元素后面有重复元素，此时奇偶互换下， **因为不能两次连续奇数跳或者偶数跳**， 如果在到达当前元素是偶数跳， 那么下一步应该是奇数跳。

如果不存在当前元素时，直接访问比当前元素大， 小的两个元素， 小的元素状态更新到 even 里， 大的元素状态更新到 odd 里。
状态更新过程中同样需要奇偶互换，因为不能两次连续奇数跳或者偶数跳，我这一步是奇数跳，下一步就是偶数跳。我把下一步偶数跳的状态更新过来就是我能不能跳到末尾了。

最后统计奇偶任一数组中为 true 的数目即可

TC: O(nlogn)
SC: O(n)
```kotlin
import java.util.*

class Solution {
    fun oddEvenJumps(arr: IntArray): Int {
        if (arr.size <= 1) return arr.size
        val even = BooleanArray(arr.size)
        val odd = BooleanArray(arr.size)
        odd[odd.lastIndex] = true
        even[even.lastIndex] = true
        val visited = TreeMap<Int, Int>()
        visited[arr.last()] = arr.lastIndex
        for (i in arr.lastIndex - 1 downTo 0) {
            if (arr[i] in visited) {
                odd[i] = even[visited.getValue(arr[i])]
                even[i] = odd[visited.getValue(arr[i])]
            } else {
                val lower = visited.lowerKey(arr[i])
                val higher = visited.higherKey(arr[i])
                // even point lower value 
                if (lower != null) {
                    even[i] = odd[visited.getValue(lower)]
                }
                // odd point higher value
                if (higher != null) {
                    odd[i] = even[visited.getValue(higher)]
                }
            }
            visited[arr[i]] = i
        }
        return odd.count { it }
    }
}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/odd-even-jump/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
