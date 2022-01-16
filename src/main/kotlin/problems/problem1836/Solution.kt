package problems.problem1836

import datastructures.ListNode

class Solution {
  fun deleteDuplicatesUnsorted(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    val duplicates = getDuplicates(head)
    var cur = head
    var prev: ListNode? = null
    var newHead: ListNode? = null
    while (cur != null) {
      val isDuplicate = duplicates.contains(cur.`val`)
      if (!isDuplicate) {
        if (newHead == null) {
          newHead = cur
          prev = cur
          cur = cur.next
          prev.next = null
        }
        else {
          prev?.next = cur
          prev = cur
          cur = cur.next
          prev.next = null
        }
      }
      else {
        cur = cur.next
      }
    }

    return newHead
  }

  private fun getDuplicates(head: ListNode): Set<Int> {
    var cur: ListNode? = head
    val frequencyMap = mutableMapOf<Int, Int>()
    while (cur != null) {
      frequencyMap.merge(cur.`val`, 1) { cur, acc -> cur + acc }
      cur = cur.next
    }

    val duplicateEntries = frequencyMap.entries.filter { it.value > 1 }
    return duplicateEntries.map { it.key }.toSet()
  }
}