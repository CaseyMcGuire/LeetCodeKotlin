package problems.problem0147

import datastructures.ListNode

class Solution {
  fun insertionSortList(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    var newHead = head!!
    var cur = head?.next
    newHead.next = null

    while (cur != null) {
      val next = cur.next
      cur.next = null
      if (newHead.`val` > cur.`val`) {
        cur.next = newHead
        newHead = cur
        cur = next
        continue
      }
      var iter: ListNode? = newHead
      var prev: ListNode? = null
      while (true) {
        if (iter == null) {
          prev?.next = cur
          break
        }

        if (cur.`val` < iter.`val`) {
          prev?.next = cur
          cur.next = iter
          break
        }
        prev = iter
        iter = iter.next
      }
      cur = next
    }
    return newHead
  }
}