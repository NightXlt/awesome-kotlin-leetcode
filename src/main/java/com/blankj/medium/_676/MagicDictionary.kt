package com.blankj.medium._676

import com.blankj.ext.print

class Trie {

    var children = mutableMapOf<Char, Trie>()
        private set

    var isWord = false
        private set

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.isWord = true
    }


}

class MagicDictionary() {
    val trie = Trie()
    fun buildDict(dictionary: Array<String>) = dictionary.forEach {
        trie.insert(it)
    }

    fun search(searchWord: String): Boolean {
        return dfs(searchWord, trie, 0, 0)
    }

    private fun dfs(searchWord: String, trie: Trie?, i: Int, editCount: Int): Boolean {
        if (trie == null) return false
        // 因为每个字符都要处理，处理完 lastIndex 后 +1 就变成 size 了
        if (trie.isWord && i == searchWord.length && editCount == 1) {
            return true
        }
        if (i < searchWord.length && editCount <= 1) {
            var found: Boolean
            for (c in 'a'..'z') {
                val next = if (c == searchWord[i]) editCount else editCount + 1
                found = dfs(searchWord, trie.children[c], i + 1, next)
                if (found) {
                    return true
                }
            }
        }
        return false
    }

}

fun main() {
    val magicDictionary = MagicDictionary()
    magicDictionary.buildDict(arrayOf("hello", "leetcode"))
    magicDictionary.search("hello").print()
    magicDictionary.search("hhllo").print()
}