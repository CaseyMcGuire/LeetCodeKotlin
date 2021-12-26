package problems.problem0082

import datastructures.ListNode

class Solution {
  fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    var newHead: ListNode? = null
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
      var next = cur
      var count = 0
      while (next != null) {
        if (cur.`val` == next.`val`) {
          count++
        }
        else {
          break
        }
        next = next.next
      }

      if (count == 1) {
        prev?.next = cur
        prev = cur
        if (newHead == null) {
          newHead = cur
        }
        cur = next
        prev.next = null
      }
      else {
        cur = next
      }
    }
    return newHead
  }
}