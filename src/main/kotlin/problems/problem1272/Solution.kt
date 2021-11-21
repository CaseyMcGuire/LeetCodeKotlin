package problems.problem1272

class Solution {
  fun removeInterval(intervals: Array<IntArray>, toBeRemoved: IntArray): List<List<Int>> {
    val intervalList = intervals.map { Interval(it[0], it[1]) }
    val removeInterval = Interval(toBeRemoved[0], toBeRemoved[1])
    val newIntervals = mutableListOf<Interval>()
    val toRemove = mutableListOf<Interval>()

    for (interval in intervalList) {
      if (removeInterval.overlaps(interval)) {
        toRemove.add(interval)
      }
      else if (!removeInterval.overlaps(interval) && toRemove.isNotEmpty()) {
        val modified = getModifiedIntervals(removeInterval, toRemove)
        newIntervals.addAll(modified)
        toRemove.clear()
        newIntervals.add(interval)
      }
      else {
        newIntervals.add(interval)
      }
    }

    if (toRemove.isNotEmpty()) {
      newIntervals.addAll(getModifiedIntervals(removeInterval, toRemove))
    }
    return newIntervals.map { it.toList() }
  }

  private fun getModifiedIntervals(toRemove: Interval, overlapping: MutableList<Interval>): List<Interval> {
    if (overlapping.isEmpty()) {
      return listOf()
    }
    val modifiedIntervals = mutableListOf<Interval>()
    // take care of case where removed interval is entirely contained in an interval
    if (overlapping.size == 1 && overlapping[0].contains(toRemove)) {
      val only = overlapping[0]
      if (only.start != toRemove.start) {
        modifiedIntervals.add(Interval(only.start, toRemove.start))
      }
      if (only.end != toRemove.end) {
        modifiedIntervals.add(Interval(toRemove.end, only.end))
      }
      return modifiedIntervals
    }


    for (interval in overlapping) {
      // if interval is contained in removed interval, just delete it entirely
      if (toRemove.contains(interval)) {
        continue
      }
      // only two cases left are first and last interval
      if (interval.start < toRemove.start) {
        modifiedIntervals.add(Interval(interval.start, toRemove.start))
      }

      if (interval.end > toRemove.end) {
        modifiedIntervals.add(Interval(toRemove.end, interval.end))
      }

    }
    return modifiedIntervals
  }
}

data class Interval(val start: Int, val end: Int) {
  fun overlaps(other: Interval): Boolean {
    val otherRange = other.start until other.end
    val thisRange = this.start until this.end
    return this.start in otherRange || this.end in otherRange || other.start in thisRange || other.end in thisRange
  }

  fun contains(other: Interval): Boolean {
    return other.start in this.start until this.end && other.end in this.start until this.end
  }

  fun toList(): List<Int> {
    return listOf(this.start, this.end)
  }
}