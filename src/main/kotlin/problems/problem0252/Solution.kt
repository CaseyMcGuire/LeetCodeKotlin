package problems.problem0252

class Solution {
  fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
    val intervalList = intervals.map { Interval(it[0], it[1]) }.sortedBy { it.start }
    for (i in 0 until intervalList.size - 1) {
      val first = intervalList[i]
      val second = intervalList[i + 1]
      if (first.overlap(second)) {
        return false
      }
    }
    return true
  }
}

data class Interval(val start: Int, val end: Int) {
  fun overlap(other: Interval): Boolean {
    return this.start in other.start until end || this.end - 1 in other.start until other.end || other.start in this.start until this.end || other.end - 1 in this.start until this.end
  }
}