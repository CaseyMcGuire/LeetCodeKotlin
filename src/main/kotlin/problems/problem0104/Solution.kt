package problems.problem0104

import datastructures.TreeNode

class Solution {
  fun maxDepth(root: TreeNode?): Int {
    fun recurse(node: TreeNode?, currentDepth: Int): Int {
      if (node == null) {
        return currentDepth
      }
      val leftDepth = recurse(node.left, currentDepth + 1)
      val rightDepth = recurse(node.right, currentDepth + 1)
      return if (leftDepth > rightDepth) leftDepth else rightDepth
    }
    return recurse(root, 0)
  }
}