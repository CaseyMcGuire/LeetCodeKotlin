package problems.problem0333

import datastructures.TreeNode

class Solution {
  fun largestBSTSubtree(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    var largestSoFar = -1
    fun recurse(node: TreeNode): Result? {
      val left = node.left
      val right = node.right
      val aloneResult = Result(node.`val`, node.`val`, 1)
      if (left == null && right == null) {
        if (largestSoFar == -1) {
          largestSoFar = 1
        }
        return aloneResult
      }

      var leftResult = if (left != null) recurse(left) else null
      var rightResult = if (right != null) recurse(right) else null

      if (leftResult == null && left != null || rightResult == null && right != null) {
        return null
      }
      val value = node.`val`
      val lessThanRight = if (rightResult == null) true else value < rightResult.smallest
      val greaterThanLeft = if (leftResult == null) true else value > leftResult.largest

      if (lessThanRight && greaterThanLeft) {
        val largest = if (rightResult != null) rightResult.largest else value
        val smallest = if (leftResult != null) leftResult.smallest else value
        val rightSize = rightResult?.size ?: 0
        val leftSize = leftResult?.size ?: 0
        val thisSize = 1 + rightSize + leftSize
        if (thisSize > largestSoFar) {
          largestSoFar = thisSize
        }
        return Result(largest, smallest, thisSize)
      }
      else {
        return null
      }
    }
    recurse(root)
    return largestSoFar
  }
}

data class Result(val largest: Int, val smallest: Int, val size: Int)