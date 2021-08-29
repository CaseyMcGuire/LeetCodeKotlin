package problems.problem0759

class Solution {
  fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): ArrayList<Interval> {
    val flattenedSchedule = mutableListOf<Interval>()
    for (employeeSchedule in schedule) {
      for (interval in employeeSchedule) {
        flattenedSchedule.add(interval)
      }
    }
    if (flattenedSchedule.isEmpty()) {
      return ArrayList()
    }
    val mergedIntervals = mergeIntervals(flattenedSchedule.sortedBy { it.start })
    val freeIntervals = mutableListOf<Interval>()
    for (i in 0 until mergedIntervals.size - 1) {
      val currentInterval = mergedIntervals[i]
      val nextInterval = mergedIntervals[i + 1]
      val freeInterval = Interval(currentInterval.end, nextInterval.start)
      freeIntervals.add(freeInterval)
    }
    return ArrayList(freeIntervals)
  }

  private fun mergeIntervals(intervals: List<Interval>): MutableList<Interval> {
    var currentInterval = intervals[0]
    val mergedIntervals = mutableListOf<Interval>()
    for (i in 1 until intervals.size) {
      val nextInterval = intervals[i]
      val shouldMerge = currentInterval.end >= nextInterval.start
      if (shouldMerge) {
        currentInterval = mergeInterval(currentInterval, nextInterval)
      }
      else {
        mergedIntervals.add(currentInterval)
        currentInterval = nextInterval
      }
    }
    mergedIntervals.add(currentInterval)
    return mergedIntervals
  }

  private fun mergeInterval(first: Interval, second: Interval): Interval {
    val start = if (first.start < second.start) first.start else second.start
    val end = if (first.end > second.end) first.end else second.end
    return Interval(start, end)
  }
}

class Interval {
		var start:Int = 0
		var end:Int = 0
		constructor(_start:Int, _end:Int) {
			start = _start
			end = _end
		}
}