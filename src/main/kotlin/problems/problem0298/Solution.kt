package problems.problem0298

import datastructures.TreeNode

class Solution {
  fun longestConsecutive(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    var longestSoFar = -1
    fun recurse(node: TreeNode, parent: TreeNode?, pathLength: Int) {
      val isInPath = parent == null || parent.`val` + 1 == node.`val`

      val newPathLength = if (isInPath) pathLength + 1 else 1
      if (newPathLength > longestSoFar) {
        longestSoFar = newPathLength
      }
      val left = node.left
      val right = node.right
      if (left != null) {
        recurse(left, node, newPathLength)
      }

      if (right != null) {
        recurse(right, node, newPathLength)
      }
    }
    recurse(root, null, 0)
    return longestSoFar
  }
}