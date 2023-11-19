package com.blankj.medium._049

import com.blankj.ext.print

class GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val result = mutableListOf<MutableList<String>>()
        if (strs.isNullOrEmpty()) return result
        val map = strs.groupBy {
            getBitmapKey(it)
        }
        return map.values.toList()
    }

    private val map = IntArray(26)

    private fun getBitmapKey(str: String): String {
        map.fill(0)
        for (c in str) {
            map[c - 'a']++
        }
        return buildString {
            map.indices.filter { map[it] != 0 }
                .forEach {
                    append('a' + it)
                    append('_')
                    append(map[it])
                }
        }
    }
}

fun main() {
    GroupAnagrams().groupAnagrams(
        arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    ).print()
    GroupAnagrams().groupAnagrams(
        arrayOf("cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc")
    ).print()
    print('a' + 2)
}