package com.blankj.hard._212

class Solution {
    val dirs = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
    )

    fun dfs(board: Array<CharArray>, node: Trie, row: Int, column: Int, res: MutableList<String>) {
        if (row !in board.indices || column !in board[row].indices) {
            return
        }
        val c = board[row][column]
        if (c == '.' || !node.children.containsKey(c)) {
            return
        }
        var trieNode = node
        trieNode.children[c]?.run {
            trieNode = this
        }
        trieNode.word?.run {
            res.add(this)
            trieNode.word = null
        }
        board[row][column] = '.'
        for ((rowDiff, colDiff) in dirs) {
            dfs(board, trieNode, row + rowDiff, column + colDiff, res)
        }
        board[row][column] = c
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        if (board.isEmpty() || words.isEmpty()) {
            return emptyList()
        }
        val res = mutableListOf<String>()
        val root = Trie()
        words.forEach {
            root.insert(it)
        }
        for (i in board.indices) {
            for (j in 0 until board[0].size) {
                dfs(board, root, i, j, res)
            }
        }
        return res
    }

}

class Trie {

    var children = mutableMapOf<Char, Trie>()
    var word: String? = null

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var cur = this
        word.forEach {
            cur = cur.children.getOrPut(it) { Trie() }
        }
        cur.word = word
    }
}