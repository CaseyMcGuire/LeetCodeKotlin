package problems.problem0814

import datastructures.TreeNode

class Solution {
  fun pruneTree(root: TreeNode?): TreeNode? {
    val hasOneInSubtree = hasOneInSubtree(root)
    if (!hasOneInSubtree) {
      return null
    }
    return root
  }

  private fun hasOneInSubtree(root: TreeNode?): Boolean {
    if (root == null) {
      return false
    }
    val isOne = root.`val` == 1
    if (root.left == null && root.right == null) {
      return isOne
    }

    val leftHasOnes = hasOneInSubtree(root.left)
    val rightHasOnes = hasOneInSubtree(root.right)
    if (!leftHasOnes) {
      root.left = null
    }

    if (!rightHasOnes) {
      root.right = null
    }

    return leftHasOnes || rightHasOnes || isOne
  }
}