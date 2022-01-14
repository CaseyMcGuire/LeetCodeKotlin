package problems.problem0111

import datastructures.TreeNode

class Solution {
  fun minDepth(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    fun recurse(node: TreeNode): Int {
      if (node.isLeaf()) {
        return 1
      }

      if (node.left == null) {
        return recurse(node.right!!) + 1
      }
      else if (node.right == null) {
        return recurse(node.left!!) + 1
      }
      return Math.min(recurse(node.left!!) + 1, recurse(node.right!!) + 1)
    }
    return recurse(root)
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}