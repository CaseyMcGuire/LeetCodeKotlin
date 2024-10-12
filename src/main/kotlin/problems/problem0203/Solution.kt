package problems.problem0203

import datastructures.ListNode

class Solution {
  fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    var cur = head
    while (cur != null && cur.`val` == `val`) {
      cur = cur.next
    }

    if (cur == null) {
      return null
    }

    val newHead = cur
    while (cur != null) {
      if (cur.next?.`val` == `val`) {
        cur.next = cur.next?.next
      }
      else {
        cur = cur.next
      }
    }
    return newHead
  }
}