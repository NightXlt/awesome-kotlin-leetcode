package com.blankj.medium._033

import kotlinx.coroutines.flow.callbackFlow

// https://nightxlt.github.io/2018/12/31/Searching%20Rotated%20array%2f
class Search {
    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + ((right - left) shr 1)
            if (nums[mid] == target) {
                return mid
            }
            if (nums[mid] >= nums[left]) {
                if (nums[mid] > target && target >= nums[left]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }
        return -1
    }
}