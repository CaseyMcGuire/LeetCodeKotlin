package problems.problem0658

import java.util.*

class Solution {
  fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    if (k == 0) {
      return emptyList()
    }

    val closestElements = mutableListOf<Int>()
    val lessThan = PriorityQueue<Int>(compareBy({ -it }))
    val greaterThan = PriorityQueue<Int>(compareBy({ it }))
    for (elem in arr) {
      if (elem < x) {
        lessThan.add(elem)
      }
      else {
        greaterThan.add(elem)
      }
    }

    while (k > closestElements.size && (lessThan.isNotEmpty() || greaterThan.isNotEmpty())) {
      val element = if (lessThan.isEmpty()) {
        greaterThan.poll()
      }
      else if (greaterThan.isEmpty()) {
        lessThan.poll()
      }
      else {
        val lessDifference = Math.abs(lessThan.peek() - x)
        val greaterDifference = Math.abs(greaterThan.peek() - x)
        if (lessDifference <= greaterDifference) {
          lessThan.poll()
        }
        else {
          greaterThan.poll()
        }
      }
      closestElements.add(element)
    }

    return closestElements.sorted()
  }
}