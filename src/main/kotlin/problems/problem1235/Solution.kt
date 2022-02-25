package problems.problem1235

import java.util.*

class Solution {
  fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
    val jobs = mutableListOf<Job>()
    for (i in startTime.indices) {
      jobs.add(Job(startTime[i], endTime[i], profit[i]))
    }
    jobs.sortBy { it.start }
    val startTimeToMaxProfit = TreeMap<Int, Int>()
    var largestSoFar = 0
    for (i in jobs.indices.reversed()) {
      val curJob = jobs[i]
      var curProfit = curJob.profit
      val maxProfitsAhead = startTimeToMaxProfit.tailMap(curJob.end, true)
      if (maxProfitsAhead.isNotEmpty()) {
        curProfit += maxProfitsAhead.firstEntry().value
      }
      largestSoFar = Math.max(largestSoFar, curProfit)
      startTimeToMaxProfit[curJob.start] = largestSoFar
    }
    return largestSoFar
  }
}

data class Job(val start: Int, val end: Int, val profit: Int)