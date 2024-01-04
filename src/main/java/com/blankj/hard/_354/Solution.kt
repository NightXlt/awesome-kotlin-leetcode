package com.blankj.hard._354

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun maxEnvelopes(envelopes: Array<IntArray>): Int {
        if (envelopes.isEmpty()) return 0
        envelopes.sortWith(compareBy({ it[0] }, { -it[1] }))
        var len = -1
        val h = IntArray(envelopes.size)
        for (i in envelopes.indices) {
            if (len == -1 || envelopes[i][1] > h[len]) {
                h[++len] = envelopes[i][1]
                continue
            }
            var low = 0
            var high = len
            while (low <= high) {
                val m = (low + high) / 2
                if (h[m] < envelopes[i][1]) {
                    low = m + 1
                } else {
                    high = m - 1
                }
            }
            h[low] = envelopes[i][1]
        }
        return len + 1
    }
}

fun main() {
    Solution().maxEnvelopes(
        MultiDimensionArray.createTestData("[[5,4],[6,4],[6,7],[2,3]]")
    ).print()
    Solution().maxEnvelopes(
        MultiDimensionArray.createTestData("[[1,1],[1,1],[1,1]]")
    ).print()
}