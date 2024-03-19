package com.blankj.hard._76

import com.blankj.ext.print

class Solution {

    fun minWindow(s: String?, t: String?): String? {
        if (s.isNullOrEmpty() || t.isNullOrEmpty() || s.length < t.length) return ""

        val tFreq = IntArray(128)
        val windowFreq = IntArray(128)
        t.forEach { tFreq[it.code]++ }
        var left = 0
        var right = 0
        var count = 0
        var minLen = Int.MAX_VALUE
        var start = 0
        while (right < s.length) {
            val c = s[right]
            // 移除掉考虑 tFreq[c.code] == 0 再 continue 的特殊处理， 使逻辑更加简洁
            if (windowFreq[c.code] < tFreq[c.code]) {
                count++
            }
            windowFreq[c.code]++
            right++
            while (count == t.length) {
                if (right - left < minLen) {
                    minLen = right - left
                    start = left
                }
                val leftChar = s[left]
                // 移除掉考虑 tFreq[c.code] == 0 再 continue 的特殊处理， 使逻辑更加一致
                if (tFreq[leftChar.code] == windowFreq[leftChar.code]) {
                    count--
                }
                windowFreq[leftChar.code]--
                left++
            }
        }
        return if (minLen == Int.MAX_VALUE) "" else s.substring(start, start + minLen)
    }
}

fun main() {
    Solution().minWindow("a", "aa")?.print()
    Solution().minWindow("", "aa")?.print()
    Solution().minWindow(null, "aa")?.print()
    Solution().minWindow("ADOBECODEBANC", "ABC")?.print()
    Solution().minWindow(
        "wegdtzwabazduwwdysdetrrctotpcepalxdewzezbfewbabbseinxbqqplitpxtcwwhuyntbtzxwzyaufihclztckdwccpeyonumbpnuonsnnsjscrvpsqsftohvfnvtbphcgxyumqjzltspmphefzjypsvugqqjhzlnylhkdqmolggxvneaopadivzqnpzurmhpxqcaiqruwztroxtcnvhxqgndyozpcigzykbiaucyvwrjvknifufxducbkbsmlanllpunlyohwfsssiazeixhebipfcdqdrcqiwftutcrbxjthlulvttcvdtaiwqlnsdvqkrngvghupcbcwnaqiclnvnvtfihylcqwvderjllannflchdklqxidvbjdijrnbpkftbqgpttcagghkqucpcgmfrqqajdbynitrbzgwukyaqhmibpzfxmkoeaqnftnvegohfudbgbbyiqglhhqevcszdkokdbhjjvqqrvrxyvvgldtuljygmsircydhalrlgjeyfvxdstmfyhzjrxsfpcytabdcmwqvhuvmpssingpmnpvgmpletjzunewbamwiirwymqizwxlmojsbaehupiocnmenbcxjwujimthjtvvhenkettylcoppdveeycpuybekulvpgqzmgjrbdrmficwlxarxegrejvrejmvrfuenexojqdqyfmjeoacvjvzsrqycfuvmozzuypfpsvnzjxeazgvibubunzyuvugmvhguyojrlysvxwxxesfioiebidxdzfpumyon",
        "ozgzyywxvtublcl"
    )?.print()
}