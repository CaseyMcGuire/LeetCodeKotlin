package problems.problem0124

import datastructures.TreeNode
import java.util.*

class Solution {
  fun maxPathSum(root: TreeNode?): Int {
    var maximumSumSoFar: Int? = null
    fun maxSinglePathSum(node: TreeNode?): Int {
      if (node == null) {
        return 0
      }
      val leftSum = maxSinglePathSum(node.left)
      val rightSum = maxSinglePathSum(node.right)
      val totalSum = leftSum + rightSum + node.`val`
      if (maximumSumSoFar == null || maximumSumSoFar!! < totalSum) {
        maximumSumSoFar = totalSum
      }
      val leftTotalSum = leftSum + node.`val`
      val rightTotalSum = rightSum + node.`val`
      if (leftTotalSum < 0 && rightTotalSum < 0) {
        return 0
      }
      return if (leftTotalSum > rightTotalSum) {
        leftTotalSum
      }
      else {
        rightTotalSum
      }
    }
    maxSinglePathSum(root)

    return maximumSumSoFar ?: 0
  }
}

