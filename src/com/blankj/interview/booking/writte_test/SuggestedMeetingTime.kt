package com.blankj.interview.booking.writte_test

import com.blankj.ext.print

const val NO_TIME_AVAILABLE = "No time available"
// booking 面试平台： https://www.hackerrank.com/onboarding?h_l=domains&h_r=hrw&utm_source=hrwCandidateFeedback&redirect=%2Fdomains
fun suggestedMeetingTime(
    workingHours: Pair<Int, Int>,
    meetings: List<Pair<Int, Int>>,
    currentTime: Int,
    duration: Int
): String {
    if (workingHours.second <= workingHours.first
        || meetings.isEmpty()
        || currentTime <= 0
        || duration <= 0
    ) {
        return NO_TIME_AVAILABLE
    }
    val (startWorkTime, endWorkTime) = workingHours
    val sortedMeetings = meetings.sortedBy { it.first }
    var curTime = currentTime
    if (currentTime < startWorkTime) {
        curTime = startWorkTime
    }
    sortedMeetings.forEachIndexed { index, (startMeetingTime, endMeetingTime) ->
        val expectedMeetingEndTime = curTime + duration
        if (
            ((expectedMeetingEndTime <= startMeetingTime && index == 0)
                    || (index >0 && curTime >= sortedMeetings[index - 1].second && expectedMeetingEndTime <= startMeetingTime)
                    )
            && expectedMeetingEndTime <= endWorkTime
        ) {
            return "$curTime-$expectedMeetingEndTime"
        }
        if (endMeetingTime >= curTime) {
            curTime = endMeetingTime
        }
    }
    if (curTime + duration <= endWorkTime) {
        return "$curTime-${curTime + duration}"
    }
    return NO_TIME_AVAILABLE
}

fun main() {
    suggestedMeetingTime(
        Pair(480, 1080),
        listOf(
            Pair(600, 660),
            Pair(720, 780),
            Pair(780, 825),
            Pair(900, 940),
        ),
        780,
        45
    ).print()
}