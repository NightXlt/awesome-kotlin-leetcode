package com.blankj.pramp

class Decrypt {

    fun decrypt(word: String): String {
        val s = StringBuilder()
        var oldValue = 1
        var newValue: Int
        val a = 'a'.code

        for (i in word.indices) {
            newValue = word[i].code - oldValue
            oldValue = word[i].code

            while (newValue < a) {
                newValue += 26
            }
            s.append(newValue.toChar())
        }
        return s.toString()
    }
}