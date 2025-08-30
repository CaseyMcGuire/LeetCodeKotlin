package problems.problem1235

import java.util.*

class Solution {
  fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
    val jobs = mutableListOf<Job>()
    for (i in startTime.indices) {
      jobs.add(Job(startTime[i], endTime[i], profit[i]))
    }

    jobs.sortBy { it.startTime }

    val startTimeToMaxProfit = TreeMap<Int, Int>()

    var maxProfitSoFar = 0

    for (i in jobs.indices.reversed()) {
      val curJob = jobs[i]

      // get the next job that starts after this one ends
      val highestProfitIncludingThisJob = startTimeToMaxProfit.higherEntry(curJob.endTime - 1)
      val profit = if (highestProfitIncludingThisJob == null) {
        curJob.profit
      }
      else {
        curJob.profit + highestProfitIncludingThisJob.value
      }

      val nextJobStartTime = jobs.getOrNull(i + 1)?.startTime ?: -1
      val profitNotIncludingJob = startTimeToMaxProfit[nextJobStartTime] ?: 0
      val maxProfit = Math.max(profitNotIncludingJob, profit)
      maxProfitSoFar = Math.max(maxProfit, maxProfitSoFar)

      val existingMaxProfit = startTimeToMaxProfit[curJob.startTime] ?: 0
      startTimeToMaxProfit[curJob.startTime] = Math.max(maxProfit, existingMaxProfit)
    }
    return maxProfitSoFar
  }
}

data class Job(val startTime: Int, val endTime: Int, val profit: Int)