package problems.problem0849

import java.util.*

class Solution {
  fun maxDistToClosest(seats: IntArray): Int {
    val gaps = LinkedList<Interval>()
    var currentStart: Int? = null
    for (i in seats.indices) {
      val seat = seats[i]
      if (seat == 0) {
        if (currentStart == null) {
          currentStart = i
        }
      }
      else {
        if (currentStart != null) {
          gaps.add(Interval(currentStart, i))
          currentStart = null
        }
      }
    }
    if (currentStart != null) {
      gaps.add(Interval(currentStart, seats.size))
    }
    val left = if (gaps.peekFirst().i == 0)  gaps.removeFirst() else null
    val right = if (gaps.isNotEmpty() && gaps.peekLast().j == seats.size) gaps.removeLast() else null
    val largestMiddle = gaps.map { it.getSize() }.max()
    var largestSoFar = 0
    if (left != null) {
      largestSoFar = left.getSize()
    }

    if (right != null) {
      if (largestSoFar < right.getSize()) {
        largestSoFar = right.getSize()
      }
    }

    if (largestMiddle != null) {
      val length = largestMiddle
      val distance = if (length % 2 == 0) {
        length / 2
      }
      else {
        length / 2 + 1
      }
      if (largestSoFar < distance) {
        largestSoFar = distance
      }
    }

    return largestSoFar
  }
}

data class Interval(val i: Int, val j: Int) {
  fun getSize(): Int {
    return j - i
  }
}