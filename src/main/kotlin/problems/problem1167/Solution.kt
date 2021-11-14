package problems.problem1167

import java.util.*

class Solution {
  fun connectSticks(sticks: IntArray): Int {
    val pq = PriorityQueue<Int>()
    for (stick in sticks) {
      pq.add(stick)
    }

    var sum = 0
    while (pq.size > 1) {
      val first = pq.poll()
      val second = pq.poll()
      val cost = first + second
      sum += cost
      pq.add(cost)
    }
    return sum
  }
}