package problems.problem2046

import datastructures.ListNode

class Solution {
  fun sortLinkedList(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    var curHead: ListNode = head
    var cur: ListNode? = head.next
    var prev: ListNode = head
    while (cur != null) {
      val next = cur.next
      if (cur.`val` <= curHead.`val`) {
        prev.next = next
        cur.next = curHead
        curHead = cur
        cur = next
      }
      else {
        prev = cur
        cur = cur.next
      }
    }
    return curHead
  }
}