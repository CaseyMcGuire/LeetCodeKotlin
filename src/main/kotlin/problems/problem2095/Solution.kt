package problems.problem2095

import datastructures.ListNode

class Solution {
  fun deleteMiddle(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }

    val listLength = getListLength(head)
    if (listLength == 1) {
      return null
    }

    val middleNodeIndex = listLength / 2
    var cur = head!!
    var i = 0
    while (i < middleNodeIndex - 1) {
      cur = cur.next!!
      i++
    }
    cur.next = cur.next?.next
    return head
  }

  private fun getListLength(head: ListNode): Int {
    var cur: ListNode? = head
    var length = 0
    while (cur != null) {
      length++
      cur = cur.next
    }
    return length
  }
}