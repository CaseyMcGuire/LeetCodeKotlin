package problems.problem0729

import java.util.*

class MyCalendar() {

  private val intervalStarts = TreeMap<Int, Interval>()

  fun book(start: Int, end: Int): Boolean {
    val interval = Interval(start, end)
    val closestToStart = intervalStarts.floorEntry(interval.start)?.value
    val closestToEnd = intervalStarts.lowerEntry(interval.end)?.value
    if (closestToStart != null && interval.overlaps(closestToStart)) {
      return false
    }
    if (closestToEnd != null && interval.overlaps(closestToEnd)) {
      return false
    }
    intervalStarts[interval.start] = interval
    return true
  }

}

data class Interval(val start: Int, val end: Int) {
  fun overlaps(other: Interval): Boolean {
    val (lowerStart, higherStart) = if (other.start < this.start) Pair(other, this) else Pair(this, other)
    return higherStart.start in lowerStart.start until lowerStart.end ||
        higherStart.end in lowerStart.start until lowerStart.end
  }
}