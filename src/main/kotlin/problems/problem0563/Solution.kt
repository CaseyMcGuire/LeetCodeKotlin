package problems.problem0563

import datastructures.TreeNode

class Solution {
  fun findTilt(root: TreeNode?): Int {
    var totalTilt = 0
    fun recurse(node: TreeNode?): Int {
      if (node == null) {
        return 0
      }
      val leftTilt = recurse(node.left)
      val rightTilt = recurse(node.right)
      totalTilt += Math.abs(leftTilt - rightTilt)
      return leftTilt + rightTilt + node.`val`
    }
    recurse(root)
    return totalTilt
  }
}