package com.blankj.coding_interviews._69


class Solution {

    fun isNumber(s: String): Boolean {
        // Finite state machine
        val transfer: MutableMap<State, Map<CharType, State>> = mutableMapOf()

        val initialMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_SPACE to State.STATE_INITIAL,
            CharType.CHAR_NUMBER to State.STATE_INTEGER,
            CharType.CHAR_POINT to State.STATE_POINT_WITHOUT_INT,
            CharType.CHAR_SIGN to State.STATE_INT_SIGN
        )
        transfer[State.STATE_INITIAL] = initialMap

        val intSignMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_INTEGER,
            CharType.CHAR_POINT to State.STATE_POINT_WITHOUT_INT
        )
        transfer[State.STATE_INT_SIGN] = intSignMap

        val integerMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_INTEGER,
            CharType.CHAR_EXP to State.STATE_EXP,
            CharType.CHAR_POINT to State.STATE_POINT,
            CharType.CHAR_SPACE to State.STATE_END
        )
        transfer[State.STATE_INTEGER] = integerMap

        val pointMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_FRACTION,
            CharType.CHAR_EXP to State.STATE_EXP,
            CharType.CHAR_SPACE to State.STATE_END
        )
        transfer[State.STATE_POINT] = pointMap

        val pointWithoutIntMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_FRACTION
        )
        transfer[State.STATE_POINT_WITHOUT_INT] = pointWithoutIntMap

        val fractionMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_FRACTION,
            CharType.CHAR_EXP to State.STATE_EXP,
            CharType.CHAR_SPACE to State.STATE_END
        )
        transfer[State.STATE_FRACTION] = fractionMap

        val expMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_EXP_NUMBER,
            CharType.CHAR_SIGN to State.STATE_EXP_SIGN
        )
        transfer[State.STATE_EXP] = expMap

        val expSignMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_EXP_NUMBER
        )
        transfer[State.STATE_EXP_SIGN] = expSignMap

        val expNumberMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_NUMBER to State.STATE_EXP_NUMBER,
            CharType.CHAR_SPACE to State.STATE_END
        )
        transfer[State.STATE_EXP_NUMBER] = expNumberMap

        val endMap: Map<CharType, State> = hashMapOf(
            CharType.CHAR_SPACE to State.STATE_END
        )
        transfer[State.STATE_END] = endMap
        val length = s.length

        var state: State? = State.STATE_INITIAL
        for (i in 0 until length) {
            val type: CharType = toCharType(s[i])
            state = if (!transfer[state]!!.containsKey(type)) {
                return false
            } else {
                transfer[state]!![type]
            }
        }
        return state == State.STATE_INTEGER
                || state == State.STATE_POINT
                || state == State.STATE_FRACTION
                || state == State.STATE_EXP_NUMBER
                || state == State.STATE_END
    }

    private fun toCharType(ch: Char): CharType {
        return when (ch) {
            in '0'..'9' -> CharType.CHAR_NUMBER
            'e', 'E' -> CharType.CHAR_EXP
            '.' -> CharType.CHAR_POINT
            '+', '-' -> CharType.CHAR_SIGN
            ' ' -> CharType.CHAR_SPACE
            else -> CharType.CHAR_ILLEGAL
        }
    }

    enum class State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END
    }

    enum class CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_SPACE,
        CHAR_ILLEGAL
    }
}

fun main() {

}