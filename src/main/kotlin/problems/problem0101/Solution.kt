package problems.problem0101

import datastructures.TreeNode

class Solution {
  fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) {
      return true
    }
    if (root.left == null && root.right == null) {
      return true
    }
    var left = mutableListOf(root.left)
    var right = mutableListOf(root.right)

    while (left.isNotEmpty() && right.isNotEmpty()) {

      val leftValues = left.map { it?.`val` }
      val rightValues = right.map { it?.`val` }
      if (leftValues != rightValues.reversed()) {
        return false
      }
      val nextLeft = mutableListOf<TreeNode?>()
      val nextRight = mutableListOf<TreeNode?>()

      for (i in left.indices) {
        val curLeft = left[i]
        if (curLeft != null) {
          nextLeft.add(curLeft.left)
          nextLeft.add(curLeft.right)
        }

        val curRight = right[i]
        if (curRight != null) {
          nextRight.add(curRight.left)
          nextRight.add(curRight.right)
        }

      }
      left = nextLeft
      right = nextRight
    }
    return true

  }
}