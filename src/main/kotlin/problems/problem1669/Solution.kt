package problems.problem1669

import datastructures.ListNode

class Solution {
  fun mergeInBetween(list1: ListNode?, a: Int, b: Int, list2: ListNode?): ListNode? {
    var cur = list1
    var i = 0
    while (i < a - 1) {
      cur = cur?.next
      i++
    }

    var next = cur?.next
    cur?.next = list2
    cur = next
    while (i < b) {
      cur = cur?.next
      i++
    }

    val list2Last = list2?.last()
    list2Last?.next = cur

    return list1
  }

  fun ListNode.last(): ListNode {
    var cur: ListNode? = this
    while (cur?.next != null) {
      cur = cur?.next
    }
    return cur!!
  }
}