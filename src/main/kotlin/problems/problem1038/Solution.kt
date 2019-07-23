package problems.problem1038

import datastructures.TreeNode

class Solution {
  fun bstToGst(root: TreeNode?): TreeNode? {
    recurse(root, 0)
    return root
  }

  private fun recurse(root: TreeNode?, runningSum: Int): Int {
    if (root == null) {
      return runningSum
    }
    val rightSum = recurse(root.right, runningSum)
    val currentNodeSum = rightSum + root.`val`
    root.`val` = currentNodeSum
    return recurse(root.left, currentNodeSum)
  }
}