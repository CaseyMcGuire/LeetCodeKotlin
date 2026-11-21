package problems.problem1029

import java.util.PriorityQueue

class Solution {
  fun twoCitySchedCost(costs: Array<IntArray>): Int {
    val pq = PriorityQueue<IntArray>(compareByDescending { Math.abs(it[0] - it[1]) })
    for (cost in costs) {
      pq.add(cost)
    }

    var numA = costs.size / 2
    var numB = costs.size / 2
    var total = 0
    while (pq.isNotEmpty()) {
      val next = pq.poll()
      if (numA == 0) {
        total += next[1]
        numB--
      }
      else if (numB == 0) {
        total += next[0]
        numA--
      }
      else if (next[0] > next[1]) {
        total += next[1]
        numB--
      }
      else {
        total += next[0]
        numA--
      }
    }
    return total
  }
}