package com.blankj.medium._380

import kotlin.random.Random

class RandomizedSet {

    private val nums = mutableListOf<Int>()
    private val numToLocation = mutableMapOf<Int, Int>()

    fun insert(`val`: Int): Boolean {
        if (numToLocation.contains(`val`)) return false
        numToLocation[`val`] = nums.size
        nums.add(`val`)
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!numToLocation.contains(`val`)) return false
        val index = numToLocation.getValue(`val`)
        val lastElement = nums.last()
        nums[index] = lastElement.also { nums[nums.lastIndex] = nums[index] }
        numToLocation[lastElement] = index
        nums.removeLast()
        numToLocation.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        val index = Random.nextInt(nums.size)
        return nums[index]
    }

}

fun main() {
    val randomizedSet = RandomizedSet()
    randomizedSet.insert(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.remove(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.insert(0) // Inserts 1 to the set. Returns true as 1 was inserted successfully.


}