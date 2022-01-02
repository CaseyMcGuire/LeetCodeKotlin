package problems.problem0086

import datastructures.ListNode

class Solution {
  fun partition(head: ListNode?, x: Int): ListNode? {
    var headFirst: ListNode? = null
    var headSecond: ListNode? = null
    var prevFirst: ListNode? = null
    var prevSecond: ListNode? = null
    var cur = head
    while (cur != null) {
      val value = cur.`val`
      val next = cur.next
      cur.next = null
      if (value < x) {
        if (headFirst == null) {
          headFirst = cur
          prevFirst = cur
        }
        else {
          prevFirst?.next = cur
          prevFirst = cur
        }
      }
      else {
        if (headSecond == null) {
          headSecond = cur
          prevSecond = cur
        }
        else {
          prevSecond?.next = cur
          prevSecond = cur
        }
      }
      cur = next
    }

    prevFirst?.next = headSecond
    return if (headFirst != null) headFirst else headSecond
  }
}