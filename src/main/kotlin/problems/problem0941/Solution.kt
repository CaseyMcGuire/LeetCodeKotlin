package problems.problem0941

class MyCalendarTwo() {

  var intervals = mutableListOf<Interval>()
  val doubleIntervals = mutableListOf<Interval>()

  fun book(start: Int, end: Int): Boolean {
    val curInterval = Interval(start, end)
    for (interval in doubleIntervals) {
      if (interval.overlaps(curInterval)) {
        return false
      }
    }

    val overlapping = mutableListOf<Interval>()
    for (interval in intervals) {
      if (interval.overlaps(curInterval)) {
        overlapping.add(interval)
      }
    }
    if (overlapping.isEmpty()) {
      intervals.add(curInterval)
      return true
    }

    for(overlap in overlapping) {
      doubleIntervals.add(overlap.getOverlap(curInterval))
    }

    val newIntervals = mutableListOf<Interval>()
    intervals.add(curInterval)
    intervals.sortBy { it.start }
    var toMerge = intervals[0]
    for (i in 1 until intervals.size) {
      val cur = intervals[i]
      if (!toMerge.overlaps(cur)) {
        newIntervals.add(toMerge)
        toMerge = cur
      }
      else {
        toMerge = toMerge.merge(cur)
      }
    }
    newIntervals.add(toMerge)
    intervals = newIntervals
    return true
  }

}

data class Interval(val start: Int, val end: Int) {
  fun overlaps(other: Interval): Boolean {
    val thisRange = this.start until this.end
    val otherRange = other.start until other.end
    return this.start in otherRange || this.end - 1 in otherRange || other.start in thisRange || other.end - 1 in thisRange
  }

  fun getOverlap(other: Interval): Interval {
    val greaterStart = if (this.start > other.start) this.start else other.start
    val smallerEnd = if (this.end < other.end) this.end else other.end
    return Interval(greaterStart, smallerEnd)
  }

  fun merge(other: Interval): Interval {
    val smallerStart = if (this.start < other.start) this.start else other.start
    val greaterEnd = if (this.end > other.end) this.end else other.end
    return Interval(smallerStart, greaterEnd)
  }
}