package com.blankj.hard._76

import com.blankj.coding_interviews._004.print

class Solution {

    fun minWindow(s: String?, t: String?): String? {
        if (s.isNullOrEmpty() || t.isNullOrEmpty() || s.length < t.length) return ""

        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        val tFreq = IntArray(128)
        val windowFreq = IntArray(128)

        //将目标字符串指定字符的出现次数记录
        for (element in t) {
            tFreq[element.toInt()]++
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
            if (tFreq[rightChar.toInt()] == 0) {
                right++
                continue
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (windowFreq[rightChar.toInt()] < tFreq[rightChar.toInt()]) {
                count++
            }
            //已有字符串中目标字符出现的次数+1
            windowFreq[rightChar.toInt()]++
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
                if (tFreq[leftChar.toInt()] == 0) {
                    left++
                    continue
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (windowFreq[leftChar.toInt()] == tFreq[leftChar.toInt()]) {
                    count--
                }
                //已有字符串中目标字符出现的次数-1
                windowFreq[leftChar.toInt()]--
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

fun main() {
    Solution().minWindow("a", "aa")?.print()
    Solution().minWindow("", "aa")?.print()
    Solution().minWindow(null, "aa")?.print()
    Solution().minWindow("ADOBECODEBANC", "ABC")?.print()
}