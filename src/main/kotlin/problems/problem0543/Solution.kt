package problems.problem0543

import datastructures.TreeNode

class Solution {
  fun diameterOfBinaryTree(root: TreeNode?): Int {
    var longestPathSoFar = 0
    fun lengthOfLongestPath(node: TreeNode?): Int {
      if (node == null) {
        return 0
      }
      val leftLongestPath = lengthOfLongestPath(node.left)
      val rightLongestPath = lengthOfLongestPath(node.right)
      val currentLength = leftLongestPath + rightLongestPath + 1
      if (currentLength > longestPathSoFar) {
        longestPathSoFar = currentLength
      }
      return if (leftLongestPath > rightLongestPath) {
        leftLongestPath + 1
      } else {
        rightLongestPath + 1
      }
    }
    lengthOfLongestPath(root)
    return longestPathSoFar - 1
  }
}