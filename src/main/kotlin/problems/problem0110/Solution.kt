package problems.problem0110

import datastructures.TreeNode

class Solution {
  fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) {
      return true
    }
    var allEqual = true
    fun recurse(node: TreeNode?): Int {
      if (node == null) {
        return 0
      }
      if (node.isLeaf()) {
        return 1
      }

      val left = recurse(node.right)
      val right = recurse(node.left)

      if (Math.abs(left - right) > 1) {
        allEqual = false
      }

      return Math.max(left, right) + 1
    }
    recurse(root)
    return allEqual
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}