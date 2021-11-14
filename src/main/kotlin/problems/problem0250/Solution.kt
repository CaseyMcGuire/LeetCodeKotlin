package problems.problem0250

import datastructures.TreeNode

class Solution {
  fun countUnivalSubtrees(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }

    var numUnivalueSubtrees = 0
    fun dfs(node: TreeNode): Int? {
      val value = node.`val`
      val left = node.left
      val leftValue = if (left != null) dfs(left) else value

      val right = node.right
      val rightValue = if (right != null) dfs(right) else value

      if (value == leftValue && value == rightValue) {
        numUnivalueSubtrees++
        return value
      }
      return null
    }
    dfs(root)
    return numUnivalueSubtrees
  }
}