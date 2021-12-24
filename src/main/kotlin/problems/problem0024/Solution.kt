package problems.problem0024

import datastructures.ListNode

class Solution {
  fun swapPairs(head: ListNode?): ListNode? {
    if (head == null || head.next == null) {
      return head
    }
    val newHead = head.next

    var cur = head
    var prevTail: ListNode? = null
    while (cur != null) {
      val first = cur
      val second = cur.next
      if (second == null) {
        prevTail?.next = first
        break
      }
      val next = second.next
      second.next = first
      first.next = null
      prevTail?.next = second
      prevTail = first
      cur = next
    }

    return newHead
  }
}