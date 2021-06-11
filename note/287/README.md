# [Find the Duplicate Number][title]

## Solution
这道题很容易联想起[数组中的重复数字那道题](https://github.com/NightXlt/awesome-kotlin-leetcode/blob/dev/note/coding_interviews/_003/README.md),

尽管其时间复杂度达标了O(n)，但是改变了原数组不符合要求。但思路类似，当 i -> num\[i\]时，不断这么连起来，像链表一样，如果有重复数字，那么这个链表肯定有环
比如
```
1 4 6 6 6 2 3 通过 i -> num[i]得到的顺序如下
           
1 -> 4 -> 6 -> 3
          ^    |
          |    |
           <————
```
可以看到这里构成了环形链表，那么问题变成了找到环开始的节点，就是环形链表II 那道题的解法，通过快慢指针两次遍历定位到环开始的位置

有个小细节，为了避免一开始的 slow == fast 直接退出循环，通过 do,while 使得其先执行一次，再执行判断。do,while 的用法还挺少见的

```kotlin
    fun findDuplicate(nums: IntArray): Int {
        var slow = 0
        var fast = 0
        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)
        slow = 0
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }
        return slow
    }
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/find-the-duplicate-number/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
