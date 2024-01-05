package com.blankj.medium._211

class Trie {

    var children = mutableMapOf<Char, Trie>()
        private set

    var isFinished = false
        private set

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.isFinished = true
    }

}

class WordDictionary() {

    val trie = Trie()

    fun addWord(word: String) {
        trie.insert(word)
    }

    fun search(word: String): Boolean {
        fun search(trie: Trie, word: String, pos: Int): Boolean {
            if (pos == word.length ) return trie.isFinished
            return when {
                word[pos] == '.' -> trie.children.values.any { search(it, word, pos + 1) }
                else -> word[pos] in trie.children && search(trie.children[word[pos]]!!, word, pos + 1)
            }
        }
        return search(trie, word, 0)
    }

}

fun main() {

}
