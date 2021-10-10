package problems.problem0253

import java.util.*

class Solution {
  fun minMeetingRooms(intervals: Array<IntArray>): Int {
    val intervalList = intervals.map { Interval(it[0], it[1]) }.sortedBy {it.start}
    var currentMinimum: Int? = null
    val pq = PriorityQueue<Interval>(compareBy( { it.end } ))
    for (interval in intervalList) {
      while (pq.isNotEmpty() && pq.peek().end <= interval.start) {
        pq.poll()
      }
      pq.add(interval)
      if (currentMinimum == null || currentMinimum < pq.size) {
        currentMinimum = pq.size
      }
    }
    return currentMinimum ?: 0
  }
}

data class Interval(val start: Int, val end: Int)