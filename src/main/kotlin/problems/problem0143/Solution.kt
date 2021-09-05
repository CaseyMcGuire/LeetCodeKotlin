package problems.problem0143

import datastructures.ListNode
import java.util.*

class Solution {
  fun reorderList(head: ListNode?): Unit {
    if (head == null || head.next == null) {
      return
    }
    val linkedList = LinkedList<ListNode>()
    var currentNode = head
    while (currentNode != null) {
      linkedList.addLast(currentNode)
      val prev = currentNode
      currentNode = currentNode.next
      prev.next = null
    }
    var previousTail: ListNode? = null
    while (linkedList.isNotEmpty()) {
      val first = linkedList.removeFirst()
      previousTail?.next = first
      if (linkedList.isEmpty()) {
        continue
      }
      val second = linkedList.removeLast()
      first.next = second
      previousTail = second
    }

  }
}