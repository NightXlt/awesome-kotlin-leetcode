# [Longest Substring Without Repeating Characters][title]

## Description

Given a string, find the length of the **longest substring** without repeating characters.

**Examples:**

Given `"abcabcbb"`, the answer is `"abc"`, which the length is 3.

Given `"bbbbb"`, the answer is `"b"`, with the length of 1.

Given `"pwwkew"`, the answer is `"wke"`, with the length of 3. Note that the answer must be a **substring**, `"pwke"` is a *subsequence* and not a substring.

**Tags:** Hash Table, Two Pointers, String


## Solution
计算不`带重复字符`的最长子字符串的长度，
因为 ASCII码是有限的，只有 256 个，开辟一个 hash 数组来存储该字符上次出现的位置后面一个
如abcabcbb, 遍历到第二个 a 前的起始和终止索引为【0，2】 , 这时遍历第二个 a， `hash[a] = 1` 就是代表 `a` 字符前一次出现的索引在 0，
之所以记录上次索引 + 1是新的无重复子串的起始索引，也为了去除掉上一个 a。
遍历该字符串，获取到当前遍历无重复子串的起始索引(preP)，与当前的索引做差获取的就是本次所需长度，从中迭代出最大值就是最终答案。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        int preP = 0, max = 0;
        int[] hash = new int[128];
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (hash[c] > preP) { // 遇到重复字符时更新起始节点
                preP = hash[c];
            }
            int l = i - preP + 1;
            hash[c] = i + 1;
            if (l > max) max = l;
        }
        return max;
    }
}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.com/problems/longest-substring-without-repeating-characters
[ajl]: https://github.com/Blankj/awesome-java-leetcode
