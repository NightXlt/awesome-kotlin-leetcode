package com.blankj.hard._126

import com.blankj.ext.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * 假设 beginword 和 endword 之间的距离是 d。每个节点可以扩展出 k 个节点。
 * 那么正常的时间复杂就是 k^d
 * 双向搜索的时间复杂度就是 k^(d/2) + k^(d/2)
 */
class Solution {
    fun findLadders(
        beginWord: String,
        endWord: String,
        wordList: List<String>
    ): List<List<String>> {
        val dict = HashSet(wordList) //未访问过的节点集合
        val res: MutableList<List<String>> = mutableListOf()
        if (!dict.contains(endWord)) return res
        val begin = mutableSetOf<String>()
        begin.add(beginWord) //从上之下集合
        val end = mutableSetOf<String>()
        end.add(endWord) //从下至上集合
        val map = mutableMapOf<String, MutableList<String>>() //存储每个节点的邻居节点
        if (doubleBfs(dict, begin, end, map, true)) {
            dfs(map, res, beginWord, endWord, ArrayDeque())
        }
        return res
    }

    private fun dfs(
        map: MutableMap<String, MutableList<String>>,
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
    private fun doubleBfs(
        dict: MutableSet<String>,
        begin: Set<String>,
        end: Set<String>,
        map: MutableMap<String, MutableList<String>>,
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
        val validNeighbors = mutableSetOf<String>() //记录本层新增节点（未在 begin 和 end 中出现过）
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
                    if (isTraversalEnd || neighborWord !in dict) {
                        continue
                    }
                    validNeighbors.add(neighborWord) // 记录本层访问节点
                    neighborWords.add(value)
                }
                chars[i] = temp
            }
        }
        //使用短路或进行判断是否找到公共节点，找到就直接返回，否则继续以本层访问节点作为下层的 begin 继续深搜
        return isTraversalEnd || doubleBfs(dict, validNeighbors, end, map, isTopDown)
    }
}

fun main() {
    Solution().findLadders(
        "hit", "cog",
        listOf("hot","dot","dog","lot","log","cog")
    ).print()
    val start = System.currentTimeMillis()
    Solution().findLadders(
        "aaaaa", "uuuuu",
        listOf(
            "aaaaa",
            "waaaa",
            "wbaaa",
            "xaaaa",
            "xbaaa",
            "bbaaa",
            "bbwaa",
            "bbwba",
            "bbxaa",
            "bbxba",
            "bbbba",
            "wbbba",
            "wbbbb",
            "xbbba",
            "xbbbb",
            "cbbbb",
            "cwbbb",
            "cwcbb",
            "cxbbb",
            "cxcbb",
            "cccbb",
            "cccwb",
            "cccwc",
            "cccxb",
            "cccxc",
            "ccccc",
            "wcccc",
            "wdccc",
            "xcccc",
            "xdccc",
            "ddccc",
            "ddwcc",
            "ddwdc",
            "ddxcc",
            "ddxdc",
            "ddddc",
            "wdddc",
            "wdddd",
            "xdddc",
            "xdddd",
            "edddd",
            "ewddd",
            "ewedd",
            "exddd",
            "exedd",
            "eeedd",
            "eeewd",
            "eeewe",
            "eeexd",
            "eeexe",
            "eeeee",
            "weeee",
            "wfeee",
            "xeeee",
            "xfeee",
            "ffeee",
            "ffwee",
            "ffwfe",
            "ffxee",
            "ffxfe",
            "ffffe",
            "wfffe",
            "wffff",
            "xfffe",
            "xffff",
            "gffff",
            "gwfff",
            "gwgff",
            "gxfff",
            "gxgff",
            "gggff",
            "gggwf",
            "gggwg",
            "gggxf",
            "gggxg",
            "ggggg",
            "wgggg",
            "whggg",
            "xgggg",
            "xhggg",
            "hhggg",
            "hhwgg",
            "hhwhg",
            "hhxgg",
            "hhxhg",
            "hhhhg",
            "whhhg",
            "whhhh",
            "xhhhg",
            "xhhhh",
            "ihhhh",
            "iwhhh",
            "iwihh",
            "ixhhh",
            "ixihh",
            "iiihh",
            "iiiwh",
            "iiiwi",
            "iiixh",
            "iiixi",
            "iiiii",
            "wiiii",
            "wjiii",
            "xiiii",
            "xjiii",
            "jjiii",
            "jjwii",
            "jjwji",
            "jjxii",
            "jjxji",
            "jjjji",
            "wjjji",
            "wjjjj",
            "xjjji",
            "xjjjj",
            "kjjjj",
            "kwjjj",
            "kwkjj",
            "kxjjj",
            "kxkjj",
            "kkkjj",
            "kkkwj",
            "kkkwk",
            "kkkxj",
            "kkkxk",
            "kkkkk",
            "wkkkk",
            "wlkkk",
            "xkkkk",
            "xlkkk",
            "llkkk",
            "llwkk",
            "llwlk",
            "llxkk",
            "llxlk",
            "llllk",
            "wlllk",
            "wllll",
            "xlllk",
            "xllll",
            "mllll",
            "mwlll",
            "mwmll",
            "mxlll",
            "mxmll",
            "mmmll",
            "mmmwl",
            "mmmwm",
            "mmmxl",
            "mmmxm",
            "mmmmm",
            "wmmmm",
            "wnmmm",
            "xmmmm",
            "xnmmm",
            "nnmmm",
            "nnwmm",
            "nnwnm",
            "nnxmm",
            "nnxnm",
            "nnnnm",
            "wnnnm",
            "wnnnn",
            "xnnnm",
            "xnnnn",
            "onnnn",
            "ownnn",
            "owonn",
            "oxnnn",
            "oxonn",
            "ooonn",
            "ooown",
            "ooowo",
            "oooxn",
            "oooxo",
            "ooooo",
            "woooo",
            "wpooo",
            "xoooo",
            "xpooo",
            "ppooo",
            "ppwoo",
            "ppwpo",
            "ppxoo",
            "ppxpo",
            "ppppo",
            "wpppo",
            "wpppp",
            "xpppo",
            "xpppp",
            "qpppp",
            "qwppp",
            "qwqpp",
            "qxppp",
            "qxqpp",
            "qqqpp",
            "qqqwp",
            "qqqwq",
            "qqqxp",
            "qqqxq",
            "qqqqq",
            "wqqqq",
            "wrqqq",
            "xqqqq",
            "xrqqq",
            "rrqqq",
            "rrwqq",
            "rrwrq",
            "rrxqq",
            "rrxrq",
            "rrrrq",
            "wrrrq",
            "wrrrr",
            "xrrrq",
            "xrrrr",
            "srrrr",
            "swrrr",
            "swsrr",
            "sxrrr",
            "sxsrr",
            "sssrr",
            "ssswr",
            "sssws",
            "sssxr",
            "sssxs",
            "sssss",
            "wssss",
            "wtsss",
            "xssss",
            "xtsss",
            "ttsss",
            "ttwss",
            "ttwts",
            "ttxss",
            "ttxts",
            "tttts",
            "wttts",
            "wtttt",
            "xttts",
            "xtttt",
            "utttt",
            "uwttt",
            "uwutt",
            "uxttt",
            "uxutt",
            "uuutt",
            "uuuwt",
            "uuuwu",
            "uuuxt",
            "uuuxu",
            "uuuuu",
            "zzzzz",
            "zzzzy",
            "zzzyy",
            "zzyyy",
            "zzyyx",
            "zzyxx",
            "zzxxx",
            "zzxxw",
            "zzxww",
            "zzwww",
            "zzwwv",
            "zzwvv",
            "zzvvv",
            "zzvvu",
            "zzvuu",
            "zzuuu",
            "zzuut",
            "zzutt",
            "zzttt",
            "zztts",
            "zztss",
            "zzsss",
            "zzssr",
            "zzsrr",
            "zzrrr",
            "zzrrq",
            "zzrqq",
            "zzqqq",
            "zzqqp",
            "zzqpp",
            "zzppp",
            "zzppo",
            "zzpoo",
            "zzooo",
            "zzoon",
            "zzonn",
            "zznnn",
            "zznnm",
            "zznmm",
            "zzmmm",
            "zzmml",
            "zzmll",
            "zzlll",
            "zzllk",
            "zzlkk",
            "zzkkk",
            "zzkkj",
            "zzkjj",
            "zzjjj",
            "zzjji",
            "zzjii",
            "zziii",
            "zziih",
            "zzihh",
            "zzhhh",
            "zzhhg",
            "zzhgg",
            "zzggg",
            "zzggf",
            "zzgff",
            "zzfff",
            "zzffe",
            "zzfee",
            "zzeee",
            "zzeed",
            "zzedd",
            "zzddd",
            "zzddc",
            "zzdcc",
            "zzccc",
            "zzccz",
            "azccz",
            "aaccz",
            "aaacz",
            "aaaaz",
            "uuuzu",
            "uuzzu",
            "uzzzu",
            "zzzzu"
        )
    ).print()
    println(System.currentTimeMillis() - start)

}