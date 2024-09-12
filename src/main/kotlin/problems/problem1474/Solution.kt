package problems.problem1474

import datastructures.ListNode

class Solution {
  fun deleteNodes(head: ListNode?, m: Int, n: Int): ListNode? {
    var cur = head
    while (cur != null) {
      for (i in 0 until m - 1) {
        cur = cur?.next
      }
      var next = cur?.next
      cur?.next = null

      for (i in 0 until n) {
        next = next?.next
      }
      cur?.next = next
      cur = next
    }
    return head
  }
}