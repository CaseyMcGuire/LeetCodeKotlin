package problems.problem0083

import datastructures.ListNode

class Solution {
  fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    var prev = head
    var cur = head
    var prevValue = cur.`val`
    while (cur != null) {
      if (cur.`val` != prev!!.`val`) {
        prev!!.next = cur
        prev = cur
      }
      cur = cur.next
    }
    prev!!.next = null
    return head
  }
}