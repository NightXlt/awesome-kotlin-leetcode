package com.blankj.medium._049

import com.blankj.coding_interviews._004.print

class GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val result = mutableListOf<MutableList<String>>()
        if (strs.isNullOrEmpty()) return result
        val map = strs.groupBy {
            getBitmapKey(it)
        }
        return map.values.toList()
    }

    private fun getBitmapKey(str: String): String {
        val map = IntArray(26)
        for (c in str) {
            map[c - 'a']++
        }
        val sb = StringBuilder()
        for (i in map.indices) {
            if (map[i] != 0) {
                val c = 'a' + i
                // Avoid creating extra String builder
                sb.append(c)
                sb.append('_')
                sb.append(map[i])
            }
        }
        return sb.toString()
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