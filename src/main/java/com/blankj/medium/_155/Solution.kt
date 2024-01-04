package com.blankj.medium._155


class MinStack {
    private var mStack: ArrayDeque<Int> = ArrayDeque()
    private var mMinStack: ArrayDeque<Int> = ArrayDeque()


    fun push(x: Int) {
        mStack.add(x)
        if (mMinStack.size == 0 || x < mMinStack.last()) {
            mMinStack.add(x)
        } else {
            mMinStack.add(mMinStack.last())
        }
    }

    fun pop() {
        if (!mStack.isEmpty()) {
            mStack.removeLast()
            mMinStack.removeLast()
        }
    }

    fun top(): Int {
        return mStack.last()
    }

    fun getMin(): Int = mMinStack.last()
}