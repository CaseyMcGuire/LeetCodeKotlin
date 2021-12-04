package problems.problem0545

import datastructures.TreeNode

class Solution {
  fun boundaryOfBinaryTree(root: TreeNode?): List<Int> {
    if (root == null) {
      return emptyList()
    }
    val leftBoundary = mutableListOf<Int>()
    val rightBoundary = mutableListOf<Int>()
    fun recurse(node: TreeNode?, level: Int, isLeft: Boolean, isBoundary: Boolean) {
      if (node == null) {
        return
      }
      val boundary = if (isLeft) leftBoundary else rightBoundary

      if (level == boundary.size && isBoundary || node.isLeaf()) {
        boundary.add(node.`val`)
      }
      if (isLeft) {
        recurse(node.left, level + 1, isLeft, isBoundary)
        recurse(node.right, level + 1, isLeft, node.left == null && isBoundary)
      }
      else {
        recurse(node.right, level + 1, isLeft, isBoundary)
        recurse(node.left, level + 1, isLeft, node.right == null && isBoundary)
      }
    }
    recurse(root.left, 0, true, true)
    recurse(root.right, 0, false, true)
    return listOf(root.`val`) + leftBoundary + rightBoundary.reversed()
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}