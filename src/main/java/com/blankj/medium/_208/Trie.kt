package com.blankj.medium._208

class Trie {

    private var children = HashMap<Char, Trie>()
    private var isFinished = false

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.isFinished = true
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        var cur = this
        word.forEach {
            if (!cur.children.containsKey(it)) {
                return false
            }
            cur = cur.children[it]!!
        }
        return cur.isFinished
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        var cur = this
        prefix.forEach {
            if (!cur.children.containsKey(it)) {
                return false
            }
            cur = cur.children[it]!!
        }
        return true
    }
}

fun main() {
    val trie = Trie()
    println(trie.insert("apple"))
    println(trie.search("apple"))
    println(trie.search("app"))
    println(trie.startsWith("app"))
    println(trie.insert("app"))
    println(trie.search("app"))
}