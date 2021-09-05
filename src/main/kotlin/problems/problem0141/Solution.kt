package problems.problem0141

import datastructures.ListNode

class Solution {
  fun hasCycle(head: ListNode?): Boolean {
    if (head == null) {
      return false
    }
    val visited = mutableSetOf<ListNode>()
    var currentNode = head
    while (currentNode != null) {
      if (!visited.add(currentNode)) {
        return true
      }
      currentNode = currentNode.next
    }
    return false
  }
}