package com.blankj.zoom

import java.io.File

//class Dierectory {
//    val layers = mutableMapOf<String, Dierectory>()
//    var isEnd = false
//    var file: File? = null
//    var name: String = ""
//}

class File {
    var isDir = false
    var files: MutableList<File>? = null
    var ids: MutableList<Int> = mutableListOf()
    val mapping: MutableMap<Int, com.blankj.zoom.File> = mutableMapOf()
    var id: Int = 0
    var parentId: Int? = null
    var name = ""
    fun addFile(previousId: Int, path: String) {
        val paths = path.split("/")
        ids.add(ids.last() + 1)
        mapping.getValue(previousId).files
    }

    fun removeFile(previousId: Int, targetId: Int) : Boolean{
        val file = mapping[previousId] ?: error("The previous id doesn;t exist")
        if (!file.isDir) return false
        return file.files!!.removeIf { file.id == targetId }
    }

    fun getPath(curId: Int): String {
        val file = mapping[curId] ?: error("id doesn't exist")
        val path = ArrayDeque<String>()
        path.add(name)
        var parentId = file.parentId
        while (parentId != null) {
            val parent = mapping[parentId] ?: error("id doesn't exist")
            path.add(parent.name)
            parentId = parent.parentId
        }
        return path.joinToString(separator = "/")
    }
}
