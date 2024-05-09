package problems.problem0109

import datastructures.ListNode
import datastructures.TreeNode

class Solution {
  fun sortedListToBST(head: ListNode?): TreeNode? {
    val lst = mutableListOf<Int>()
    var cur = head
    while (cur != null) {
      lst.add(cur.`val`)
      cur = cur.next
    }

    fun recurse(start: Int, end: Int): TreeNode? {
      if (start > end) {
        return null
      }

      val mid = (start + end) / 2
      val node = TreeNode(lst[mid])
      node.right = recurse(mid + 1, end)
      node.left = recurse(start, mid - 1)
      return node
    }
    return recurse(0, lst.size - 1)
  }
}