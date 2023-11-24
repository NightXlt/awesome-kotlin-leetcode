package com.blankj.medium._677

import com.blankj.ext.print

class Trie {

    private var children = HashMap<Char, Trie>()
    private var value = 0

    /** Inserts a word into the trie. */
    fun insert(word: String, value: Int) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.value = value
    }


    fun sum(string: String): Int {
        var cur = this
        for (c in string) {
            if (!cur.children.containsKey(c)) {
                return 0
            }
            cur = cur.children[c]!!
        }
        return getSum(cur)
    }

    private fun getSum(trie: Trie): Int {
        var sum = 0
        for (child in trie.children.values) {
            sum += getSum(child)
        }
        sum += trie.value
        return sum
    }
}

class MapSum {
    private val trie = Trie()

    fun insert(key: String, `val`: Int) {
        trie.insert(key, `val`)
    }

    fun sum(prefix: String): Int {
        return trie.sum(prefix)
    }

}

fun main() {
    val mapSum = MapSum()
    mapSum.insert("apple", 3)
    mapSum.sum("ap").print()
    mapSum.insert("app", 5)
    mapSum.sum("ap").print()
}