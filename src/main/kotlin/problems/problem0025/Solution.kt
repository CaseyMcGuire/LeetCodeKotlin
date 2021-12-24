package problems.problem0025

import datastructures.ListNode

class Solution {
  fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    if (head == null) {
      return null
    }

    var newHead: ListNode? = null
    var cur = head
    var prevTail: ListNode? = null
    while (cur != null) {
      val kGroup = cur.getNextK(k)
      if (kGroup.isEmpty()) {
        break
      }
      if (kGroup.size < k) {
        prevTail?.next = kGroup[0]
        break
      }
      val last = kGroup[kGroup.size - 1]
      if (newHead == null) {
        newHead = last
      }
      val next = last.next
      prevTail?.next = last
      prevTail = kGroup[0]
      for (i in kGroup.indices) {
        if (i == 0) {
          kGroup[i].next = null
        }
        else {
          kGroup[i].next = kGroup[i - 1]
        }
      }
      cur = next
    }
    return newHead
  }

  private fun ListNode.getNextK(k: Int): List<ListNode> {
    var cur = this
    val nodes = mutableListOf<ListNode>()
    for (i in 0 until k) {
      nodes.add(cur)
      val next = cur.next
      if (next == null) {
        break
      }
      cur = next
    }
    return nodes
  }
}