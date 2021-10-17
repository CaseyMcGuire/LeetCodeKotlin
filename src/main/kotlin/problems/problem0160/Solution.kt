package problems.problem0160

import datastructures.ListNode

class Solution {
  fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
    if (headA == null || headB == null) {
      return null
    }
    val set = mutableSetOf<ListNode>()
    var iter = headA
    while (iter != null) {
      set.add(iter)
      iter = iter.next
    }

    iter = headB
    while (iter != null) {
      if (set.contains(iter)) {
        return iter
      }
      iter = iter.next
    }
    return null
  }
}