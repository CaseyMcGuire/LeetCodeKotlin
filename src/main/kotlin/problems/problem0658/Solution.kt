package problems.problem0658

import java.util.*

class Solution {
  fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    val pq = PriorityQueue<Int>(compareByDescending({ Math.abs(it - x) }))

    for (num in arr) {
      if (pq.size < k) {
        pq.add(num)
      }
      else if (Math.abs(pq.peek() - x) > Math.abs(num - x)) {
        pq.poll()
        pq.add(num)
      }
    }
    return pq.toList().sorted()
  }
}