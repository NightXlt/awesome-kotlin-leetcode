package com.blankj.medium._648

class Trie {

    private var children = mutableMapOf<Char, Trie>()
    private var isWord = false

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.isWord = true
    }


    fun findPrefix(string: String): String {
        var cur = this
        val builder = StringBuilder()
        for (c in string) {
            if (cur.isWord || !cur.children.containsKey(c)) {
                break
            }
            cur = cur.children[c]!!
            builder.append(c)
        }
        return if (cur.isWord) builder.toString() else ""
    }
}

class Solution {
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val trie = Trie()
        for (word in dictionary) {
            trie.insert(word)
        }
        val words = sentence.split(' ').toMutableList()
        for ((i, word) in words.withIndex()) {
            val prefix = trie.findPrefix(word)
            if (prefix.isNotEmpty()) {
                words[i] = prefix
            }
        }
        return words.joinToString(" ")
    }
}