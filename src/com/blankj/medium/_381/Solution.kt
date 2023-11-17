package com.blankj.medium._381

import kotlin.random.Random

class RandomizedCollection {

    private val nums = mutableListOf<Int>()
    private val numToLocation = mutableMapOf<Int, MutableSet<Int>>()

    fun insert(`val`: Int): Boolean {

        numToLocation.putIfAbsent(`val`, mutableSetOf())
        numToLocation.getValue(`val`).add(nums.size)
        nums.add(`val`)
        return numToLocation[`val`]?.size.orEmpty() == 1
    }

    fun remove(`val`: Int): Boolean {
        if (!numToLocation.contains(`val`)) return false
        val index = numToLocation.getValue(`val`).first()
        val lastElement = nums.last()
        nums[index] = lastElement.also { nums[nums.lastIndex] = nums[index] }
        numToLocation[lastElement]?.remove(nums.lastIndex)
        numToLocation[`val`]?.remove(index)
        if (index != nums.lastIndex) {
            numToLocation[lastElement]?.add(index)
        }
        if (numToLocation.getValue(`val`).isEmpty()) {
            numToLocation.remove(`val`)
        }
        nums.removeLast()
        return true
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