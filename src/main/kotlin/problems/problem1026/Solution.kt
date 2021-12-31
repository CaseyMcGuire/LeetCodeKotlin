package problems.problem1026

import datastructures.TreeNode

class Solution {
  fun maxAncestorDiff(root: TreeNode?): Int {
    if (root == null) {
      return -1
    }
    var maxSoFar: Int? = null
    fun recurse(node: TreeNode?, low: Int, high: Int) {
      if (node == null) {
        return
      }

      val lowDifference = Math.abs(node.`val` - low)
      if (maxSoFar == null || maxSoFar!! < lowDifference) {
        maxSoFar = lowDifference
      }

      val highDifference = Math.abs(node.`val` - high)
      if (maxSoFar == null || maxSoFar!! < highDifference) {
        maxSoFar = highDifference
      }

      val newLow = Math.min(node.`val`, low)
      val newHigh = Math.max(node.`val`, high)
      recurse(node.left, newLow, newHigh)
      recurse(node.right, newLow, newHigh)
    }
    recurse(root, root.`val`, root.`val`)
    return maxSoFar!!
  }
}