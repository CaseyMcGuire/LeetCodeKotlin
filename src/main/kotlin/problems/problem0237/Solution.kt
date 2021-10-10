package problems.problem0237

import datastructures.ListNode

class Solution {
  fun deleteNode(node: ListNode?) {
    var curNode = node
    while (true) {
      var next = curNode?.next
      if (curNode == null || next == null) {
        break
      }
      curNode.`val` = next.`val`
      if (curNode.next?.next == null) {
        curNode?.next = null
      }
      else {
        curNode = curNode?.next
      }
    }
  }
}