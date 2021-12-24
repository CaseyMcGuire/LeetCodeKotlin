package problems.problem0092

import datastructures.ListNode

class Solution {
  fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || right - left == 0) {
      return head
    }
    var cur = head
    for (i in 1 until left - 1) {
      cur = cur?.next
    }


    val headOfReversed = if (left == 1) cur!! else cur?.next!!
    val headOfReversedPortion = reverseUntil(headOfReversed, right - left)

    if (left == 1) {
      return headOfReversedPortion
    }
    else {
      cur.next = headOfReversedPortion
      return head
    }
  }

  private fun reverseUntil(head: ListNode, end: Int): ListNode {
    var prev: ListNode? = head
    var cur = head.next
    prev?.next = null
    for (i in 0 until end) {
      var next = cur?.next
      cur?.next = prev
      prev = cur
      cur = next
    }
    head.next = cur
    return prev!!
  }


}