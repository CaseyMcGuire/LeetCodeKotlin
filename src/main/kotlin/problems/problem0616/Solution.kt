package problems.problem0616

class Solution {
  fun addBoldTag(s: String, words: Array<String>): String {
    val intervals = mutableListOf<Interval>()
    for (word in words) {
      for (i in 0 until s.length - word.length + 1) {
        if (s.substringEqualsAtIndex(word, i)) {
          intervals.add(Interval(i, i + word.length))
        }
      }
    }

    val sortedIntervals = intervals.sortedBy { it.start }
    val mergedIntervals = mutableListOf<Interval>()
    for (interval in sortedIntervals) {
      if (mergedIntervals.isEmpty()) {
        mergedIntervals.add(interval)
        continue
      }

      val last = mergedIntervals[mergedIntervals.size - 1]
      if (!last.intersects(interval)) {
        mergedIntervals.add(interval)
        continue
      }

      val newInterval = last.merge(interval)
      mergedIntervals[mergedIntervals.size - 1] = newInterval
    }

    val builder = StringBuilder()
    val opening = "<b>"
    val closing = "</b>"
    var intervalIndex = 0
    for (i in 0 until s.length) {
      if (intervalIndex < mergedIntervals.size) {
        val curInterval = mergedIntervals[intervalIndex]
        if (i == curInterval.start) {
          builder.append(opening)
        }
        else if (i == curInterval.end) {
          builder.append(closing)
          intervalIndex++
        }
      }
      builder.append(s[i])
    }
    if (intervalIndex == mergedIntervals.size - 1) {
      builder.append("</b>")
    }

    return builder.toString()
  }

  fun String.substringEqualsAtIndex(substring: String, index: Int): Boolean {
    val end = index + substring.length
    if (end > this.length) {
      return false
    }
    var subIndex = 0
    for (i in index until end) {
      if (this[i] != substring[subIndex]) {
        return false
      }
      subIndex++
    }
    return true
  }
}

data class Interval(val start: Int, val end: Int) {
  fun intersects(other: Interval): Boolean {
    val thisRange = this.start until this.end
    val otherRange = other.start until other.end
    return other.start in thisRange || other.end in thisRange || this.start in otherRange || this.end in otherRange
  }

  fun merge(other: Interval): Interval {
    val lowerStart = if (other.start < this.start) other.start else this.start
    val higherEnd = if (other.end > this.end) other.end else this.end
    return Interval(lowerStart, higherEnd)
  }
}