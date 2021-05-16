# [Roman to Integer][title]

## Description

Roman numerals are represented by seven different symbols: `I`, `V`, `X`, `L`, `C`, `D` and `M`.

```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

For example, two is written as `II` in Roman numeral, just two one's added together. Twelve is written as, `XII`, which is simply `X` + `II`. The number twenty seven is written as `XXVII`, which is `XX` + `V` + `II`.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not `IIII`. Instead, the number four is written as `IV`. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as `IX`. There are six instances where subtraction is used:

- `I` can be placed before `V` (5) and `X` (10) to make 4 and 9. 
- `X` can be placed before `L` (50) and `C` (100) to make 40 and 90. 
- `C` can be placed before `D` (500) and `M` (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

**Example 1:**

```
Input: "III"
Output: 3
```

**Example 2:**

```
Input: "IV"
Output: 4
```

**Example 3:**

```
Input: "IX"
Output: 9
```

**Example 4:**

```
Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
```

**Example 5:**

```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

**Tags:** Math, String


## 思路

题意是罗马数字转整型数，范围从 1 到 3999，查看下百度百科的罗马数字介绍如下：

* 相同的数字连写，所表示的数等于这些数字相加得到的数，如 Ⅲ=3；

* 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12；

* 小的数字（限于 Ⅰ、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9。

那么我们可以利用 map 来完成罗马数字的 7 个数字符号：I、V、X、L、C、D、M 和整数的映射关系，然后根据上面的解释来模拟完成即可。
左侧可能比右侧小，如果是顺序遍历需要记录前一个数字，每次访问当前下标与前一个下标对应元素做比较，如果比其大，则加上，
否则就减去前一个元素，遍历结束后额外加上最后一个节点的值。sum的初始值为-s\[0\].
而如果是逆序遍历就方便的多，遍历到当前下标时, 直接对当前元素做处理即可，没那么多边界处理，更易理解

```java
// 逆序
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int sum = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; --i) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else {
                sum += map.get(s.charAt(i));
            }
        }
        return sum;
    }
}
```

```kotlin
class Solution {
    fun romanToInt(s: String): Int {
        var res = -getValue(s[0])
        var preNum = getValue(s[0])
        s.forEachIndexed { _, c ->
            if (preNum < getValue(c)) {
                res -= preNum
            } else {
                res += preNum
            }
            preNum = getValue(c)
        }
        res += preNum
        return res
    }

    private fun getValue(c: Char): Int {
        return when (c) {
            'I' -> 1
            'V' ->
                5;
            'X' ->
                10;
            'L' ->
                50;
            'C' ->
                100;
            'D' ->
                500;
            'M' -> 1000
            else -> -1
        }
    }
}
```

## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.com/problems/roman-to-integer
[ajl]: https://github.com/Blankj/awesome-java-leetcode
