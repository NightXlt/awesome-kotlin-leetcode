package com.blankj.hard._2227

import com.blankj.ext.print
import com.blankj.medium._93.Solution

class Encrypter(keys: CharArray, values: Array<String>, dictionary: Array<String>) {

    private val encrypterMapping = Array<String?>(26) { null }
    private val decryptMapping = mutableMapOf<String, MutableList<Char>>()
    private val trie: Trie = Trie()

    init {
        for ((index, element) in keys.withIndex()) {
            encrypterMapping[element - 'a'] = values[index]
            val list = decryptMapping.getOrDefault(values[index], mutableListOf())
            list.add(element)
            decryptMapping[values[index]] = list
        }
        dictionary.forEach { trie.insert(it) }
    }

    fun encrypt(word1: String): String {
        return word1.map { encrypterMapping[it - 'a'] }
            .joinToString(separator = "")
    }

    fun decrypt(word2: String): Int {
        val word = Array(word2.length / 2) {
            word2.substring(it * 2, it * 2 + 2)
        }
        return backtracking(word, 0, StringBuilder(), trie)
    }

    private fun backtracking(word: Array<String>, start: Int, builder: StringBuilder, trie: Trie): Int {
        if (start == word.size) {
            return if (trie.isFinished) 1 else 0
        }
        var count = 0
        val characters = decryptMapping[word[start]] ?: return 0
        for (c in characters) {
            val childNode = trie.children[c] ?: continue
            builder.append(c)
            count += backtracking(word, start + 1, builder, childNode)
            builder.deleteCharAt(builder.lastIndex)
        }
        return count
    }

}

class Trie {

    var children = HashMap<Char, Trie>()
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

fun main() {
    val encrypter = Encrypter(
        charArrayOf('a', 'b', 'c', 'd'),
        arrayOf("ei", "zf", "ei", "am"),
        arrayOf("abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad")
    )
    encrypter.encrypt("abcd").print()
    encrypter.decrypt("eizfeiam").print()
}