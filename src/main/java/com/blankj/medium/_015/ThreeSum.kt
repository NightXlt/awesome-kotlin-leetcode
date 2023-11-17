package com.blankj.medium._015

import com.blankj.ext.print

class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()
        nums.sort()
        val res = mutableListOf<List<Int>>()
        for (i in 0 until nums.size - 2) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue
            }

            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break
            }
            // 下面一定是 continue, 跳过这个下标， 继续访问后面的下标， 尽管当前不可能，但后面的有可能呀
            if (nums[i] + nums[nums.size - 1] + nums[nums.size - 2] < 0) {
                continue
            }
            twoSum(i, nums, res)
        }
        return res
    }

    private fun twoSum(
        i: Int,
        nums: IntArray,
        res: MutableList<List<Int>>
    ) {
        var j = i + 1
        var k = nums.size - 1
        while (j < k) {
            val sum = nums[i] + nums[j] + nums[k]
            when {
                sum > 0 -> {
                    k--
                }

                sum < 0 -> {
                    j++
                }

                else -> {
                    res.add(listOf(nums[i], nums[j], nums[k]))
                    while (j + 1 < k && nums[j] == nums[j + 1]) {
                        j++
                    }
                    while (k - 1 > j && nums[k] == nums[k - 1]) {
                        k--
                    }
                    j++
                    k--
                }
            }
        }
    }
}

fun main() {
    ThreeSum().threeSum(intArrayOf(-1,0,1,2,-1,-4)).print()
    ThreeSum().threeSum(intArrayOf(0,1,1)).print()
    ThreeSum().threeSum(intArrayOf(0,0,0)).print()
}