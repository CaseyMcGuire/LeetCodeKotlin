package problems.problem0206

import datastructures.ListNode

class Solution {
  fun reverseList(head: ListNode?): ListNode? {
    if (head == null || head.next == null) {
      return head
    }
    var previous = head
    var current = head?.next
    var next = head?.next?.next
    head.next = null
    while (current != null) {
      current?.next = previous
      previous = current
      current = next
      next = next?.next
    }
    return previous
  }
}