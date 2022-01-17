package problems.problem0142

import datastructures.ListNode

class Solution {
  fun detectCycle(head: ListNode?): ListNode? {
    val nodes = mutableSetOf<ListNode>()
    var cur = head
    while (cur != null) {
      if (!nodes.add(cur)) {
        return cur
      }
      cur = cur.next
    }
    return null
  }
}