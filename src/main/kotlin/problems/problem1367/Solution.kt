package problems.problem1367

import datastructures.ListNode
import datastructures.TreeNode

class Solution {
  fun isSubPath(head: ListNode?, root: TreeNode?): Boolean {

    fun recurse(node: TreeNode?): Boolean {
      if (node == null) {
        return false
      }
      if (isSubPathAtNode(head, node)) {
        return true
      }
      return recurse(node.left) || recurse(node.right)
    }
    return recurse(root)
  }

  private fun isSubPathAtNode(head: ListNode?, root: TreeNode?): Boolean {
    fun recurse(curListNode: ListNode?, curTreeNode: TreeNode?): Boolean {
      if (curListNode == null) {
        return true
      }
      if (curTreeNode == null) {
        return false
      }

      if (curTreeNode.`val` != curListNode.`val`) {
        return false
      }

      return recurse(curListNode.next, curTreeNode.left) || recurse(curListNode.next, curTreeNode.right)
    }
    return recurse(head, root)
  }
}