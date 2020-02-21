package problems.problem0002

import datastructures.ListNode

class Solution {
  fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 === null) {
      return l1
    }
    else if (l2 === null) {
      return l2
    }

    val headValue = l1.`val` + l2.`val`
    val head = ListNode(headValue % 10)
    var currentNode = head
    var remainder = headValue / 10
    var first = l1.next
    var second = l2.next

    while (true) {
      if (second === null && first === null) {
        if (remainder != 0) {
          currentNode.next = ListNode(1)
        }
        break
      }

      var (nextRemainder, sum) =
        when {
          first === null -> {
            val sum = second!!.`val` + remainder
            second = second.next
            Pair(sum / 10, sum % 10)
          }
          second === null -> {
            val sum = first.`val` + remainder
            first = first.next
            Pair(sum / 10, sum % 10)
          }
          else -> {
            val sum = first.`val` + second.`val` + remainder
            first = first.next
            second = second.next
            Pair(sum / 10, sum % 10)
          }
        }
      currentNode.next = ListNode(sum)
      currentNode = currentNode.next!!
      remainder = nextRemainder
    }
    return head
  }
}