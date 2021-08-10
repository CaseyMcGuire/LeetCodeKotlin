package problems.problem0056

class Solution {
  fun merge(intervalsArr: Array<IntArray>): Array<IntArray> {
    if (intervalsArr.isEmpty()) {
      return emptyArray()
    }
    val mergedIntervals = mutableListOf<Interval>()
    val intervals = intervalsArr
      .map { Interval(it[0], it[1]) }
      .sortedBy { it.starting }
    var currentInterval = intervals[0]
    for (i in 1 until intervals.size) {
      val interval = intervals[i]
      if (currentInterval.overlaps(interval)) {
        currentInterval = currentInterval.merge(interval)
      }
      else {
        mergedIntervals.add(currentInterval)
        currentInterval = interval
      }
    }
    mergedIntervals.add(currentInterval)
    return mergedIntervals.map {it.toIntArray() }.toTypedArray()
  }

  data class Interval(val starting: Int, val ending: Int) {
    fun toIntArray(): IntArray {
      return intArrayOf(starting, ending)
    }

    fun overlaps(other: Interval): Boolean {
      return other.starting in starting..ending || other.ending in starting..ending
    }

    fun merge(other: Interval): Interval {
      val smallerStarting = if (starting < other.starting) starting else other.starting
      val largerEnding = if (ending > other.ending) ending else other.ending
      return Interval(smallerStarting, largerEnding)
    }
  }
}