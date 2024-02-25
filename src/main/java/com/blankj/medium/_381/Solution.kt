package com.blankj.medium._381

import kotlin.random.Random

class RandomizedCollection {

    private val nums = mutableListOf<Int>()
    private val numToLocation = mutableMapOf<Int, MutableSet<Int>>()

    fun insert(`val`: Int): Boolean {
        numToLocation.getOrPut(`val`) { mutableSetOf() }.add(nums.size)
        nums.add(`val`)
        return numToLocation[`val`]?.size.orEmpty() == 1
    }

    fun remove(`val`: Int): Boolean {
        val set = numToLocation[`val`] ?: return false
        val t = set.firstOrNull() ?: return false
        val res = set.remove(t)
        nums[t] = nums.last()
        numToLocation[`val`]?.remove(t)
        numToLocation[nums[t]]?.add(t)
        numToLocation[nums[t]]?.remove(nums.lastIndex)
        nums.removeLast()
        return res
    }

    fun getRandom(): Int {
        val index = Random.nextInt(nums.size)
        return nums[index]
    }

}

fun Int?.orEmpty(): Int {
    return this ?: 0
}

fun main() {
    val randomizedSet = RandomizedCollection()
    randomizedSet.insert(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.remove(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.insert(-1) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.remove(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.getRandom()
    randomizedSet.getRandom()
    randomizedSet.getRandom()
    randomizedSet.getRandom()
    randomizedSet.getRandom()
    randomizedSet.getRandom()
}