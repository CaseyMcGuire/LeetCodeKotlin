package problems.problem0019

import datastructures.ListNode

class Solution {
  fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val list = mutableListOf<ListNode>()
    var node = head
    while (node != null) {
      list.add(node)
      node = node.next
    }
    val indexToRemove = list.size - n
    if (indexToRemove == 0) {
      return head?.next
    }
    val previous = list[indexToRemove - 1]
    previous.next = previous.next?.next
    return list[0]
  }
}