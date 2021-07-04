package problems.problem0057

class Solution {
  fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val updatedIntervals =  mutableListOf<IntArray>()
    if (intervals.isEmpty()) {
      return listOf(newInterval).toTypedArray()
    }
    val indexOfFirstOverlap = intervals.indexOfFirst { it.overlaps(newInterval) }
    if (indexOfFirstOverlap == -1) {
      var inserted = false
      for (interval in intervals) {
        if (interval[0] > newInterval[0] && !inserted) {
          inserted = true
          updatedIntervals.add(newInterval)
        }
        updatedIntervals.add(interval)
      }
      if (!inserted) {
        updatedIntervals.add(newInterval)
      }
    }
    else {
      var intervalToMerge: IntArray? = newInterval
      for (i in intervals.indices) {
        if (i < indexOfFirstOverlap) {
          updatedIntervals.add(intervals[i])
        }
        else {
          if (intervalToMerge == null) {
            updatedIntervals.add(intervals[i])
          }
          else if (intervalToMerge.overlaps(intervals[i])){
            intervalToMerge = mergeIntervals(intervalToMerge, intervals[i])
          }
          else {
            updatedIntervals.add(intervalToMerge)
            updatedIntervals.add(intervals[i])
            intervalToMerge = null
          }
        }
      }
      if (intervalToMerge != null) {
        updatedIntervals.add(intervalToMerge)
      }
    }
    return updatedIntervals.toTypedArray()
  }

  private fun mergeIntervals(first: IntArray, second: IntArray): IntArray {
    val smaller = if (first[0] < second[0]) first[0] else second[0]
    val larger = if (first[1] < second[1]) second[1] else first[1]
    return intArrayOf(smaller, larger)
  }

  private fun IntArray.overlaps(other: IntArray): Boolean {
    val thisSize = this[1] - this[0]
    val otherSize = other[1] - other[0]
    val (largerInterval, smallerInterval) =  if (thisSize > otherSize) Pair(this, other) else Pair(other, this)

    return smallerInterval[0] >= largerInterval[0] && smallerInterval[0] <= largerInterval[1] ||
        smallerInterval[1] >= largerInterval[0] && smallerInterval[1] <= largerInterval[1]
  }
}

fun main(args: Array<String>) {
  Solution().insert(arrayOf(intArrayOf(1,3), intArrayOf(6,9)), intArrayOf(2,5))
}
