package com.blankj.hard._126

import com.blankj.ext.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

class Solution {
    fun findLadders(
        beginWord: String,
        endWord: String,
        wordList: List<String>
    ): List<List<String>> {
        val dict = HashSet(wordList) //未访问过的节点集合
        val res: MutableList<List<String>> = mutableListOf()
        if (!dict.contains(endWord)) return res
        val begin = HashSet<String>()
        begin.add(beginWord) //从上之下集合
        val end = HashSet<String>()
        end.add(endWord) //从下至上集合
        val map = HashMap<String, MutableList<String>>() //存储每个节点的邻居节点
        if (doubleBfs(dict, begin, end, map, true)) {
            dfs(map, res, beginWord, endWord, ArrayDeque())
        }
        return res
    }

    private fun dfs(
        map: HashMap<String, MutableList<String>>,
        res: MutableList<List<String>>,
        beginWord: String,
        endWord: String,
        queue: ArrayDeque<String>
    ) {
        queue.add(beginWord) //beginWord 可能不在集合中，需要单独添加
        if (beginWord == endWord) {
            res.add(queue.toList())
            queue.removeLast()
            return
        }
        if (map.containsKey(beginWord)) {
            map[beginWord]?.forEach {
                dfs(map, res, it, endWord, queue)
            }
        }
        queue.removeLast()
    }

    /**
     * 寻找每个节点的邻居节点
     */
    fun doubleBfs(
        dict: HashSet<String>,
        begin: Set<String>,
        end: Set<String>,
        map: HashMap<String, MutableList<String>>,
        isTopDown: Boolean
    ): Boolean {
        //当一个方向节点数为空仍未找到 中间联通节点时，返回 false
        if (begin.isEmpty()) return false
        if (begin.size > end.size) { // 选取节点数小的方向进行遍历
            return doubleBfs(dict, end, begin, map, !isTopDown)
        }
        dict.removeAll(begin)
        dict.removeAll(end) //去除已访问节点
        var isTraversalEnd = false //是否遍历结束
        val visited = HashSet<String>() //记录本层新增节点（未在 begin 和 end 中出现过）
        for (word in begin) { //遍历begin中每个单词
            val chars = word.toCharArray()
            for (i in chars.indices) {
                val temp = chars[i]
                for (c in 'a'..'z') { //暴力枚举可能的邻居节点
                    if (c == temp) continue
                    chars[i] = c
                    val neighborWord = String(chars)
                    //根据访问顺序确定 key,value.确保 key 是从上之下方向中的上面，而value 是下面。
                    val key = if (isTopDown) word else neighborWord
                    val value = if (isTopDown) neighborWord else word
                    val neighborWords = map.getOrPut(key) { mutableListOf() }

                    //如果有公共节点，遍历完本层即可结束遍历。值得注意的是，后续本层中的公共节点还是可以进入这个 if 循环进行添加
                    if (end.contains(neighborWord)) {
                        isTraversalEnd = true
                        neighborWords.add(value)
                    }
                    //如果找到了目标节点或者dict 中不包含该邻居节点，跳过本次循环
                    if (isTraversalEnd || !dict.contains(neighborWord)) {
                        continue
                    }
                    visited.add(neighborWord) // 记录本层访问节点
                    neighborWords.add(value)
                }
                chars[i] = temp
            }
        }
        //使用短路或进行判断是否找到公共节点，找到就直接返回，否则继续以本层访问节点作为下层的 begin 继续深搜
        return isTraversalEnd || doubleBfs(dict, visited, end, map, isTopDown)
    }
}

fun main() {
//    Solution().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
//    Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)).print()
}