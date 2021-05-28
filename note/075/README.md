# [颜色分类][title]

## Solution
首先想到的解决方案是双指针，用 low, high 分别指向头尾
```
while (low < high) {
    if *low < *high then
        low ++
    else if *low > *high
        swap(low, high)
        high--
    else 
        low++
        high--
}
```
但这样漏了一个点，比如 1, 0, 2.
```
    1 0 2
  low  high
   1  0  2
     low high
   1  0  2
        low
        high        
```
但这样的双指针是不对的，正确结果是 0， 1， 2.

问题出在当 low < high 时直接 low++，忽略了 low 后面的元素可能比 low 还要小
 

题解也用的是双指针，p0 指针用来交换 0,p2用来交换2. 遍历过程中把 0 移到数组的头部，2 移到数组的尾部，得到的就是所求结果

p0一开始指向数组头，p2指向数组尾。
遍历数组直至下标 大于 p2结束。
如果找到了 2，交换p2,i 指向元素， p2左移；如果i 指向元素仍旧是 2，继续交换直至 num\[2\] != 2  
如果找到了 0，交换 p0,i 指向元素，p0 右移
```kotlin
class Solution {
    fun sortColors(nums: IntArray): Unit {
        if (nums.isEmpty()) return
        var p0 = 0
        var p2 = nums.lastIndex
        var i = 0
        while (i <= p2) {
            // Assure nums[i] != 2, move 2 to p2
            while (i <= p2 && nums[i] == 2) {
                nums[i] = nums[p2].also { nums[p2] = nums[i] }
                p2--
            }
            if (nums[i] == 0) {
                nums[i] = nums[p0].also { nums[p0] = nums[i] }
                p0++
            }
            i++
        }
    }
}
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/sort-colors/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
