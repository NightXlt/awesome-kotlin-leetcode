# [二叉搜索树与双向链表][title]

## Solution
排序的二叉搜索树铁定中序遍历没商量，结合分治法思考，
以4,2,5,1,3,null,null
当遍历到 根节点4时，假设左子树已经是一个排好序的双向链表了，只需要把左子树最大的节点的后驱3指向根节点 4
并把 根节点的左子树更新为左子树最大的节点3。
中序遍历过程中递归执行上述步骤即可得到双向链表。为了循环起来，将双向链表的头结点的左子树指向双向链表的最后一个节点，
双向链表的最后一个节点的右子树指向第一个节点
具体到代码通过 pre 临时变量记录左子树最大的节点，head 存放头结点。

```java
public class Solution {

    TreeNode head, pre;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        convertNode(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void convertNode(TreeNode root) {
        if (root == null) return;
        convertNode(root.left);
        if (pre != null) {
            pre.right = root;
            root.left = pre;
        } else { // 遍历到树的最左边孩子时，记录头结点
            head = root;
        }
        pre = root;
        convertNode(root.right);
    }

    public static void main(String[] args) {
        new Solution().treeToDoublyList(TreeNode.createTestData("[4,2,5,1,3,null,null]"));
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
