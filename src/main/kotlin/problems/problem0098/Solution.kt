package problems.problem0098

import datastructures.TreeNode

class Solution {
  fun isValidBST(root: TreeNode?): Boolean {
    fun recurse(node: TreeNode?, min: Int?, max: Int?): Boolean {
      if (node == null) {
        return true
      }
      if (min != null && node.`val` <= min ||
          max != null && node.`val` >= max) {
        return false
      }
      return recurse(node.left, min, node.`val`) && recurse(node.right, node.`val`, max)
    }
    return recurse(root, null, null)
  }
}