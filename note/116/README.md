# [Populating Next Right Pointers in Each Node][title]

1. 通过层序遍历 + 逐层遍历，构建出每层的next节点。伪代码类似
```text
       // 外层的 while 循环迭代的是层数
        while (queue.isNotEmpty) {
            
            int size = queue.size();
            
            // 遍历这一层的所有节点
            
            for (i in 0..size) {
              // 从队首取出元素
                Node node = queue.poll();
                
                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
```
时间复杂度： O(n) 空间复杂度：O(n). 因为使用了队列存储每一个元素


2. 通过上一层构建出的next节点关系构建出本层的关系。如
```text
              1
         2         3
      4    5    6     7
```
对于根节点的左子树2，可以直接通过根节点的root.left.next = root.right
对于4，5同理构建出来4的next连接。但对于右子树的连接构建依赖于父节点的next节点。
比如节点5，他的next节点是6，但其是直接访问不到节点3的，可以通过父节点绕那么一下，节点3可以访问到
节点二的右子树的next指针指向的节点就是节点二的next指针节点3的左子树节点6.

## Solution
```kotlin
class Solution {
 fun connect(root: Node?): Node? {
  if (root == null) return root
  var leftmost = root
  while (leftmost != null) {
   var head = leftmost
   while (head != null) {
    head.left?.next = head.right
    head.right?.next = head.next?.left
    head = head.next
   }
   leftmost = leftmost.left
  }
  return root
 }
}
```
时间复杂度： O(n) 空间复杂度：O(1)
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
