package com.blankj.hard._220

import com.blankj.ext.print
import java.util.*

class Solution {
    fun containsNearbyAlmostDuplicate(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        val set = TreeSet<Long>()
        for (i in nums.indices) {
            val lower = set.floor(nums[i].toLong())
            if (lower != null && lower >= nums[i] - valueDiff) {
                return true
            }
            val upper = set.ceiling(nums[i].toLong())
            if (upper != null && nums[i] >= upper - valueDiff) {
                return true
            }
            set.add(nums[i].toLong())
            if (i >= indexDiff) {
                set.remove(nums[i - indexDiff].toLong())
            }
        }
        return false
    }

    fun containsNearbyAlmostDuplicateWithBucks(nums: IntArray, indexDiff: Int, valueDiff: Int): Boolean {
        val bucks = mutableMapOf<Int, Int>()
        val buckSize = valueDiff + 1
        for (i in nums.indices) {
            val buckId = getBuckID(nums[i], buckSize)
            if (buckId in bucks ||
                (buckId - 1 in bucks && valueDiff >= nums[i] - bucks.getValue(buckId - 1)) ||
                (buckId + 1 in bucks && valueDiff >= bucks.getValue(buckId + 1) - nums[i])) {
                return true
            }
            bucks[buckId] = nums[i]
            if (i >= indexDiff) {
                bucks.remove(getBuckID(nums[i - indexDiff], buckSize))
            }
        }
        return false
    }

    // 针对 负数 -1 是为了防止 0..t 和 -t..0 重合，因此将整体将负数整体左移一格
    private fun getBuckID(i: Int, buckSize: Int): Int {
        return if (i >= 0) i / buckSize else (i + 1) / buckSize - 1
    }
}

fun main() {
    Solution().containsNearbyAlmostDuplicate(
        intArrayOf(1, 2, 3, 1),
        3,
        0
    ).print()
    Solution().containsNearbyAlmostDuplicate(
        intArrayOf(1, 5, 9, 1, 5, 9),
        2,
        3
    ).print()

}