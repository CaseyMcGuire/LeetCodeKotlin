package problems.problem1290

import datastructures.ListNode

class Solution {
  fun getDecimalValue(head: ListNode?): Int {

    var sum = 0
    fun recurse(node: ListNode?): Int {
      if (node == null) {
        return 0
      }
      val curLevel = recurse(node.next)
      sum += node.`val` * Math.pow(2.0, curLevel.toDouble()).toInt()
      return curLevel + 1
    }
    recurse(head)
    return sum
  }
}