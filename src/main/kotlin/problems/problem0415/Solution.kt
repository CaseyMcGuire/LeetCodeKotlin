package problems.problem0415

import java.util.*

class Solution {
  fun addStrings(num1: String, num2: String): String {
    val queue = LinkedList<Int>()
    val (smaller, longer) = if (num1.length < num2.length) Pair(num1, num2) else Pair(num2, num1)
    var smallIndex = smaller.length - 1
    var longIndex = longer.length - 1
    var remainder = 0
    while (longIndex >= 0) {

      val curSum = if (smallIndex >= 0) {
        Character.getNumericValue(smaller[smallIndex]) +
            Character.getNumericValue(longer[longIndex]) +
            remainder
      }
      else {
        Character.getNumericValue(longer[longIndex]) +
            remainder
      }

      if (curSum >= 10) {
        queue.addFirst(curSum - 10)
        remainder = 1
      }
      else {
        queue.addFirst(curSum)
        remainder = 0
      }
      smallIndex--
      longIndex--
    }

    if (remainder == 1) {
      queue.addFirst(1)
    }
    return queue.joinToString("")
  }
}