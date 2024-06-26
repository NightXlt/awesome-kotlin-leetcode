package com.blankj.medium._146

class LRUCacheII(val capacity: Int) {
    val cachedMap = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return capacity + 1 == this.size
        }
    }
    fun get(key: Int): Int {
        return cachedMap.getOrDefault(key, -1)
    }

    fun put(key: Int, value: Int) {
        cachedMap[key] = value
    }
}