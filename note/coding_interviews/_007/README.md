# [重建二叉树][title]

## Solution
这个需要二叉树前序和中序的前置知识。
首先是前序中的第一个节点是根节点，然后根据根节点定位到其在中序中的下标 rootInorder,
rootInorder左侧是左子树， 右侧是右子树。这样可以拿到根节点左子树的长度以及右子树的长度。
从而可以知道前序中的左子树范围\[startPreorder + 1, leftPreorderEnd\]，
 右子树范围\[leftPreorderEnd + 1, endPreorder\].同理类似可推出右子树的范围。就不赘述了，详情可看下面代码
 得到左右子树在前序和中序的范围后，可将左右子树看做一颗单独的树，如果子树长度大于零，再去进行构造。

其中 leftPreorderEnd = startPreorder + leftChildLen
leftChildLen = rootInorder - startInorder


将一个大问题拆成若干小问题，如果小问题与大问题类似，可以采用递归解决，但需要注意爆栈风险 

测试用例：
空数组
在另一个数组中找不到对应数字
两个数组大小不等
不完全二叉树
完全二叉树
只有左子树
只有右子树

```kotlin
class Solution {
    var preOrder: IntArray = intArrayOf()
    var map = hashMapOf<Int, Int>()

    fun buildTree(preOrder: IntArray, inorder: IntArray): TreeNode? {
        if (preOrder.isEmpty() || inorder.isEmpty()) return null
        if (preOrder.size != inorder.size) throw IllegalArgumentException("two order array size should be equal")
        this.preOrder = preOrder
        inorder.forEachIndexed { index, i -> map[i] = index }
        return build(0, preOrder.size - 1, 0, inorder.size - 1)
    }

    private fun build(startPreorder: Int, endPreorder: Int, startInorder: Int, endInorder: Int): TreeNode? {
        val root = TreeNode(preOrder[startPreorder])
        val rootInorder = map[preOrder[startPreorder]]
                ?: throw IllegalArgumentException("Could not find out preorder number ${preOrder[startPreorder]} in inorder")
        val leftChildLen = rootInorder - startInorder
        val rightChildLen = endInorder - rootInorder
        val leftPreorderEnd = startPreorder + leftChildLen
        if (leftChildLen > 0) {
            root.left = build(startPreorder + 1, leftPreorderEnd, startInorder, rootInorder - 1)
        }
        if (rightChildLen > 0) {
            root.right = build(leftPreorderEnd + 1, endPreorder, rootInorder + 1, endInorder)
        }
        return root
    }
}
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
