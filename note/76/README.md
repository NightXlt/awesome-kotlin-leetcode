# [Minimum Window Substring][title]

## Solution
思想是通过滑动窗口解决。

- 当窗口出现的t的字符总数小于t的长度时，不断移动右边界
- 否则当窗口出现的t的字符总数大于等于t的长度时，记录此时的左右边界长度，再不断收缩左边界，判断是否仍旧包含t的所有字符，
  如果仍旧包含，那么更新最小长度，循环直至窗口出现的t的字符总数小于t的长度时
- 最终结束条件是右边界达到了数组长度，即可结束

Q: 为什么窗口出现的t的字符总数会大于t的长度呢？
A: 因为部分字符允许重复出现，比如 BABBC 和 ABC 的最小窗口就是 ABBC，因为某个唯一的字符出现的可能靠后，为了把他加入窗口，额外加了一些重复字符 

Q: 怎么记录窗口出现的t的字符总数？
A: 用一个 count 值进行记录，有且仅当当前字符 c 在 window 出现的频数小于 t 中出现的频数时，才会加一, 这里需要思考下，
count-- 只有在左边界收缩遇到一个字符在 window 出现的次数和在 t 中出现的次数一样时，表明一旦删去这个字符，window 将不再包含 t 中的所有字符串，这时就会跳出循环

具体实现里 ~~使用了 Map 记录 char 和 int 的映射，避免直接使用 int 数组需要转译, 给 map 设置初始值是为了 getValue 有默认值兜底.~~ 
因为字符是有限的，像面试官确认后，可以替换为操作更简单的 int 数组模拟 hash 表。c.toInt 在字节码层面会被直接替换为 c 的所以不虚这会有性能问题。
```kotlin
class Solution {
 fun minWindow(s: String?, t: String?): String? {
  if (s.isNullOrEmpty() || t.isNullOrEmpty() || s.length < t.length) return ""

  val windowFreq = mutableMapOf<Char, Int>().withDefault { 0 }
  val tFreq = mutableMapOf<Char, Int>().withDefault { 0 }
  //将目标字符串指定字符的出现次数记录
  for (element in t) {
   var value = tFreq.getValue(element)
   tFreq[element] = ++value
  }

  //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
  //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
  var left = 0
  var right = 0
  var min = s.length + 1
  var count = 0
  var start = 0
  while (right < s.length) {
   val rightChar = s[right]
   //说明该字符不被目标字符串需要，此时有两种情况
   // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
   // 2.循环已经开始一段时间，此处又有两种情况
   //  2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
   //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
   //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
   var charFreqInT = tFreq.getValue(rightChar)
   if (charFreqInT == 0) {
    right++
    continue
   }
   //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
   //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
   var charFreqInWindow = windowFreq.getValue(rightChar)
   if (charFreqInWindow < charFreqInT) {
    count++
   }
   //已有字符串中目标字符出现的次数+1
   windowFreq[rightChar] = charFreqInWindow + 1
   //移动右指针
   right++
   //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
   while (count == t.length) {
    //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
    if (right - left < min) {
     min = right - left
     start = left
    }
    val leftChar = s[left]
    //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
    charFreqInT = tFreq.getValue(leftChar)
    if (charFreqInT == 0) {
     left++
     continue
    }
    //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
    //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
    charFreqInWindow = windowFreq.getValue(leftChar)
    if (charFreqInWindow == charFreqInT) {
     count--
    }
    //已有字符串中目标字符出现的次数-1
    windowFreq[leftChar] = charFreqInWindow - 1
    //移动左指针
    left++
   }
  }
  //如果最小长度还为初始值，说明没有符合条件的子串
  return if (min == s.length + 1) {
   ""
  } else s.substring(start, start + min)
  //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
 }

}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/edit-distance/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
