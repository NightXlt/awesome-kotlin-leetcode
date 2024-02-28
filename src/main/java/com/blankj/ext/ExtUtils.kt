package com.blankj.ext

import com.blankj.structure.ListNode
import com.blankj.structure.TreeNode

fun Any.print() {
    when (this) {
        is Collection<*> -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        is IntArray -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        is ListNode -> {
            print("$`val` ")
            next?.print() ?: println()
        }
        is TreeNode -> {
            TreeNode.print(this)
        }
        is DoubleArray -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        is Array<*> -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        else -> println(this.toString())

    }
}