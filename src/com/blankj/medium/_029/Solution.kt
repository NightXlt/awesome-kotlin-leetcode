package com.blankj.medium._029

private class Solu {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
        if (divisor == 0) {
            throw ArithmeticException("divide zero exception")
        }
        val sign = (dividend > 0).xor(divisor > 0)
        var dividend = dividend
        var divisor = divisor
        if (dividend > 0) { // 切换为负数的目的是为了防止切换为正整数出现溢出问题， 毕竟负数最小数是 -2^31
            dividend = -dividend
        }
        if (divisor > 0) {
            divisor = -divisor
        }
        var res = divideCore(dividend, divisor)
        if (sign) return res
        return -res
    }

    private fun divideCore(dividend: Int, divisor: Int): Int {
        var dividend1 = dividend
        var res = 0
        while (dividend1 <= divisor) {
            var tempResult = -1;
            var tempDivisor = divisor;
            while (dividend1 <= (tempDivisor.shl(1))) {
                if (tempDivisor <= (Integer.MIN_VALUE.shr(1))) break;
                tempResult = tempResult.shl(1)
                tempDivisor = tempDivisor.shl(1)
            }
            dividend1 -= tempDivisor;
            res += tempResult;
        }
        return res
    }
}