# [Asteroid Collision][title]

## Solution
向左飞的行星，和后续向右飞的行星是不冲突的；但向右飞的行星，和后续向左飞的行星是冲突的；
而且碰撞的顺序其实是满足栈后进先出的顺序， 考虑用栈来模拟碰撞。

如果一颗小行星向右飞行，那么可以将它入栈。

如果一颗小行星是向左飞行的，而位于栈顶的小行星向右飞行，那么它将与位于栈顶的小行星相撞。

如果位于栈顶的小行星较小，那么它将爆炸消失，也就是说它将出栈。然后判断它是否将与下一颗位于栈顶的小行星相撞。如果小行星与栈中所有小行星相撞之后仍然没有爆炸消失，那么将它入栈
否则如果碰撞到其中一个大于 0 的元素发现我的相反数比他小， 那么这时候是不会加入到栈中的。

汇总下能加入到栈中的情况分别为， stack 在碰撞中元素都清空了； 行星是朝右飞的（不 care 栈顶元素了）； 我虽然朝左飞， 但栈顶也是朝左飞， 那也是可以加进去的。

此外需要留意的是， 栈顶和行星方向相反，但数值相等的情况。

```kotlin
class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        for (asteroid in asteroids) {
            while (stack.isNotEmpty() && stack.last() > 0 && stack.last() < -asteroid) {
                stack.removeLast()
            }
            // equals case, handle all collision case
            if (stack.isNotEmpty() && asteroid < 0 && stack.last() == -asteroid) {
                stack.removeLast()
                // stack is empty after collision, asteroid goes right or stack last elements is less than 0.
            } else if (stack.isEmpty() || asteroid > 0 || stack.last() < 0) {
                stack.add(asteroid)
            }
        }
        return stack.toIntArray()
    }
}
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/asteroid-collision/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
