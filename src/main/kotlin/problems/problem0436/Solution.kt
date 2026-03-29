package problems.problem0436

import java.util.TreeMap

class Solution {
  fun findRightInterval(intervals: Array<IntArray>): IntArray {
    val startToIndex = TreeMap<Int, Int>()
    for (i in intervals.indices) {
      val intervalStart = intervals[i][0]
      startToIndex[intervalStart] = i
    }

    val rightIntervals = IntArray(intervals.size) { 0 }
    for (i in intervals.indices) {
      val intervalEnd = intervals[i][1]
      rightIntervals[i] = startToIndex.ceilingEntry(intervalEnd)?.value ?: -1
    }
    return rightIntervals
  }
}