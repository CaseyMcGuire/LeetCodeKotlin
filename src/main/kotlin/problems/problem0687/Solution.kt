package problems.problem0687

import datastructures.TreeNode

class Solution {
  fun longestUnivaluePath(root: TreeNode?): Int {
    var longestPathSoFar = 0

    fun traverse(node: TreeNode?): Int {
      if (node == null) {
        return 0
      }

      val leftPathLength = traverse(node.left)
      val rightPathLength = traverse(node.right)

      var leftCommonValuePath = 0
      if (node.left?.`val` == node.`val`) {
        leftCommonValuePath += leftPathLength
      }


      var rightCommonValuePath = 0
      if (node.right?.`val` == node.`val`) {
        rightCommonValuePath += rightPathLength
      }

      var numCommonNodes = leftCommonValuePath + rightCommonValuePath + 1
      if (numCommonNodes > longestPathSoFar) {
        longestPathSoFar = numCommonNodes - 1
      }


      return 1 + Math.max(leftCommonValuePath, rightCommonValuePath)
    }
    traverse(root)
    return longestPathSoFar
  }
}