# [二叉搜索树的后序遍历序列][title]

## Solution
两种方法，1.分治法(剑指 Offer 上的)
利用后序和二叉搜索树的特性
1. 数组最后一个元素是根节点
2. 根据根节点确定出左右子树分界点，即第一个大于根节点数值的节点属于右子树
3. 进行分治前，先进行一遍剪枝，遍历一遍右子树区域看是否有比根节点小的节点，有的话直接返回 false
4. 分治操作
5. 递归结：当只剩两个元素时，返回 true. 毕竟只有两个元素嘛，后面一个根节点，前面的是左节点右节点都不紧要了。
时间复杂度： O(n^2); 每次调用partition(i,j)减去一个根节点，因此递归占用 O(N)；最差情况下（即当树退化为链表,只有左子树或者右子树），每轮递归都需遍历树所有节点，占用 O(N) 。
空间复杂度： O(n)。最差情况，树退化为链表，递归深度为 N

2. 单调栈
数组逆序的分布为
```
根节点 | 右子树 | 左子树
root  | xxx   | A B C D
```
所以当我们找到一个比根节点小的节点A时，表示我们找到了左子树，意味着这个节点 A 右侧的节点BCD都要比根节点小, 否则就不满足二叉搜索树的性质
参考单调栈[单调栈](https://blog.csdn.net/zuzhiang/article/details/78134247)。
实现一个单调递增栈，如果入栈元素a比栈顶元素小，栈内元素出栈直至找到比 a 小的元素。
关键点：找到第一个比 root 小的值后更新 root，此后遍历的序列s1都是 root的父节点的左子树，如果发现s1 中有比 root 大的数值就直接返回 false。
结合下图说明
以\[1,5,7,6,2\]为例
```
   2
  /  \
1     6
     /  \
    5    7
```
初始化时将整棵树作为一个根为最大值的左子树，这样可以将左子树第一个的节点入栈
```
     Int.MAX
    /
   2
  /  \
1     6
     /  \
    5    7

```
sample: \[1,5,7,6,2\]
因为栈为空，直接入栈第一个元素 2
a: 6 > 2， 直接入栈。栈S = \[2,6\] root = Int.MAX
b: 7 > 6， 直接入栈。S = \[2,6,7\] root = Int.MAX
c: 5 < 7. 出栈至栈顶元素 < 5, 并更新 root 找到 5的根节点6, 出栈至\[2\].入栈元素 5 得到 S = \[2,5\]。root = 6。
    剩余遍历的序列\[1\]是root=6的父节点左子树。所以剩余序列必定是需要比 root = 6 小的。
d: 1 < 5, 出栈至栈顶元素 < 1, 并更新 root 找到 1的根节点2, 出栈至\[\].入栈元素 1 得到S =  \[1\]。root = 2
e: 遍历结束，返回 true

同理来个反例 \[1, 6, 3, 2, 5\]
a: 入栈 5. 栈S = \[5\] root = Int.MAX
b: 2 < 5, 栈至栈顶元素 < 2, 并把出栈元素赋值给 root, 出栈至\[\].入栈元素 2 得到 S = \[2\]。root = 5。表明访问完节点 5
c: 3 > 2, 直接入栈。S = \[2,3\] root = 5
c: 6 > 5, 因为5 的父节点的左子树肯定不会比 5 大，所以该序列非法

时间复杂度：O(N)， 所有节点只遍历一次
空间复杂度：O(N), 借助栈最多存放树中所有节点
```kotlin
class Solution {
    
    fun verifyPostorder(postorder: IntArray): Boolean {
        return partition(postorder, 0, postorder.lastIndex)
    }

    private fun partition(postorder: IntArray, start: Int, end: Int): Boolean {
        if (end - start <= 1) return true
        val root = postorder[end]
        var p = start
        while (postorder[p] < root) p++
        val mid = p
        while (postorder[p] > root) p++
        return p == end && partition(postorder, start, mid - 1) && partition(postorder, mid, end - 1)
    }
    
    // Monotonic stack
    fun verifyPostorder(postorder: IntArray): Boolean {
        if (postorder.isEmpty()) return true
        var root = Int.MAX_VALUE
        val stack = ArrayDeque<Int>()
        for (i in postorder.indices.reversed()) {
            // post[i] must be root's left child, so if (postorder[i] > root) return false
            if (postorder[i] > root) return false   
            while (stack.isNotEmpty() && stack.peek() > postorder[i]) { // find out nearest root of postorder[i]
                root = stack.pop()
            }
            stack.push(postorder[i])
        }
        return true
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
