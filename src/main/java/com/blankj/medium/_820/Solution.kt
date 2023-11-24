package com.blankj.medium._820

class Trie {

    var children = HashMap<Char, Trie>()
        private set

    private var isWord = false

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        for (i in word.indices.reversed()) {
            cur = cur.children.getOrPut(word[i]) { Trie() }
        }
        cur.isWord = true
    }
}

class Solution {
    fun minimumLengthEncoding(words: Array<String>): Int {
        val trie = Trie()
        words.forEach { trie.insert(it) }
        var res = IntArray(1)
        dfs(res, trie, 1)
        return res[0]
    }

    private fun dfs(res: IntArray, trie: Trie, length: Int) {
        var isLeaf = true
        for (child in trie.children.values) {
            dfs(res, child, length + 1)
            isLeaf = false
        }
        if (isLeaf) {
            res[0] += length
        }
    }
}