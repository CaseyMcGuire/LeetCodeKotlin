package problems.problem0021

import datastructures.ListNode

class Solution {
  fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
      return l2
    } else if (l2 == null) {
      return l1
    }

    val head = if (l1.`val` < l2.`val`) l1 else l2
    val (nextL1, nextL2) =
      if (head === l1) {
        Pair(l1.next, l2)
      } else {
        Pair(l1, l2.next)
      }
    mergeLists(head, nextL1, nextL2)
    return head
  }

  private tailrec fun mergeLists(current: ListNode, l1: ListNode?, l2: ListNode?) {
    if (l1 == null && l2 == null) {
      return
    } else if (l1 == null) {
      current.next = l2
      return
    } else if (l2 == null) {
      current.next = l1
      return
    }

    val (nextL1, nextL2) =
      if (l1.`val` < l2.`val`) {
        current.next = l1
        Pair(l1.next, l2)
      } else {
        current.next = l2
        Pair(l1, l2.next)
      }

    mergeLists(current.next!!, nextL1, nextL2)
  }
}