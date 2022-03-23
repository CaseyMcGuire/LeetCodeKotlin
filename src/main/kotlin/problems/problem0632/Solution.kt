package problems.problem0632

import java.util.*

class Solution {
  fun smallestRange(nums: List<List<Int>>): IntArray {
    val pq = PriorityQueue<LinkedList<Int>>(compareBy({ it.first()!! }))
    for (num in nums) {
      pq.add(LinkedList(num))
    }

    val elems = mutableListOf<Int>()
    for (elem in pq) {
      elems.add(elem.peekFirst())
    }
    elems.sort()
    var smallestRange = Range(elems[0], elems[elems.size - 1])
    var curRange = smallestRange

    while (true) {

      val curList = pq.poll()
      curList.removeFirst()
      if (curList.isEmpty()) {
        break
      }
      pq.add(curList)

      val first = curList.first()!!
      if (first > curRange.end) {
        curRange = Range(curRange.start, first)
      }
      else {
        curRange = Range(pq.peek().first()!!, curRange.end)
      }

      if (smallestRange == null || smallestRange.getLength() > curRange.getLength()) {
        smallestRange = curRange
      }
    }
    return smallestRange!!.toIntArray()
  }
}

data class Range(val start: Int, val end: Int) {
  fun getLength(): Int {
    return end - start
  }

  fun toIntArray(): IntArray {
    return intArrayOf(start, end)
  }
}