package problems.problem2130

import datastructures.ListNode
import java.util.*

class Solution {
  fun pairSum(head: ListNode?): Int {
    val nodeList = getNodeList(head)
    var largestSumSoFar: Int? = null
    while (nodeList.isNotEmpty()) {
      val sum = nodeList.removeFirst() + nodeList.removeLast()
      if (largestSumSoFar == null || largestSumSoFar < sum) {
        largestSumSoFar = sum
      }
    }
    return largestSumSoFar!!
  }

  private fun getNodeList(head: ListNode?): LinkedList<Int> {
    var cur = head
    val list = LinkedList<Int>()
    while (cur != null) {
      list.add(cur.`val`)
      cur = cur.next
    }
    return list
  }
}