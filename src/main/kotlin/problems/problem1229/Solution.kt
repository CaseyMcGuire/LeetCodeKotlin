package problems.problem1229

class Solution {
  fun minAvailableDuration(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
    val firstIntervals = slots1.map { Interval(it[0], it[1]) }.sortedBy { it.start }
    val secondIntervals = slots2.map { Interval(it[0], it[1]) }.sortedBy { it.start }
    for (firstInterval in firstIntervals) {
      for (secondInterval in secondIntervals) {
        val overlap = firstInterval.getOverlap(secondInterval)
        if (overlap != null) {
          val length = overlap.end - overlap.start
          if (length >= duration) {
            return listOf(overlap.start, overlap.start + duration)
          }
        }
      }
    }
    return emptyList()
  }
}

data class Interval(val start: Int, val end: Int) {
  fun getOverlap(other: Interval): Interval? {
    val (earlier, later) = if (start < other.start) Pair(this, other) else Pair(other, this)

    if (later.start !in earlier.start..earlier.end) {
      return null
    }

    val start = later.start

    val end = if (earlier.end < later.end) earlier.end else later.end
    return Interval(start, end)
  }
}