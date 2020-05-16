# [Successor LCCI][title]

## Solution

紧扣住二叉搜索树的特性，左子树比我小，右子树比我大。那么遍历 root 时，如果当前 root.val <= target.val，则 root 必不是 target的后驱，毕竟 中序情况下 target 排在 root 后面嘛。
而如果 root.val > target.val 那么root 可能是 target 的后驱。

下面分为两种实现迭代和递归：

```java
public class Solution {

    public TreeNode inorderSuccessorIterate(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.right != null) { // p 的右子树不空则遍历其右子树最左下节点
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }
        TreeNode res = null; // 保存root后驱
        while (root != p) { // p 没有右子树，check out p 有木有父节点
            if (root.val < p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.val >= root.val) return inorderSuccessor(root.right, p); // 加上等号表示找到 p 后再遍历其右子树最左下的节点
        TreeNode left = inorderSuccessor(root.left, p);
        return left != null ? left : root;
    }
    
}


```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/successor-lcci/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
