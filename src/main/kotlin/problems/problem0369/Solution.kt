package problems.problem0369

import datastructures.ListNode
import java.util.*

class Solution {
  fun plusOne(head: ListNode?): ListNode? {
    if (head == null || head.next == null && head.`val` == 0) {
      return ListNode(1)
    }
    var currentNode = head
    val stack = ArrayDeque<ListNode>()
    while (currentNode != null) {
      stack.push(currentNode)
      currentNode = currentNode.next
    }

    var remainder = 1
    while (stack.isNotEmpty() && remainder == 1) {
      val cur = stack.pop()
      val newVal = cur.`val` + remainder
      if (newVal == 10) {
        cur.`val` = 0
      }
      else {
        cur.`val` = newVal
        remainder = 0
      }
    }

    if (remainder == 1) {
      val newHead = ListNode(remainder)
      newHead.next = head
      return newHead
    }
    else {
      return head
    }
  }
}