package problems.problem1326

class Solution {
  fun minTaps(n: Int, ranges: IntArray): Int {
    val intervals = ranges.mapIndexed { index, elem -> Interval(Math.max(0, index - ranges[index]), Math.min(index + ranges[index], n)) }
      .sortedWith(compareBy({ it.start }, { -it.getLength() }))
    // note: sorting by length above is important
    val minIntervals = mutableListOf<Interval>()
    for (interval in intervals) {
      if (minIntervals.isEmpty()) {
        minIntervals.add(interval)
        continue
      }
      val prev = minIntervals.last()

      // we found a gap that no interval cover
      if (!prev.contains(interval.start)) {
        return -1
      }

      // current interval is contained in previous
      if (interval.end <= prev.end) {
        continue
      }

      // remove intervals that overlap previous interval
      var i = minIntervals.size - 2
      var numToRemove = 0
      while (i >= 0) {
        val curInterval = minIntervals[i]
        if (curInterval.contains(interval.start)) {
          numToRemove++
        }
        i--
      }

      for (i in 0 until numToRemove) {
        minIntervals.removeAt(minIntervals.size - 1)
      }
      minIntervals.add(interval)
    }

    val first = minIntervals.first()
    val last = minIntervals.last()
    if (first.start > 0 || last.end < n) {
      return -1
    }
    return minIntervals.size
  }
}

data class Interval(val start: Int, val end: Int) {
  fun contains(n: Int): Boolean {
    return n in start..end
  }

  fun getLength(): Int {
    return end - start
  }
}