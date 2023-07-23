package problems.problem0872

import datastructures.TreeNode

class Solution {
  fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
    return getLeafLevel(root1) == getLeafLevel(root2)
  }

  private fun getLeafLevel(root: TreeNode?): List<Int> {
    if (root == null) {
      return emptyList()
    }

    val numList = mutableListOf<Int>()
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }

      if (node.left == null && node.right == null) {
        numList.add(node.`val`)
        return
      }
      recurse(node.left)
      recurse(node.right)
    }
    recurse(root)
    return numList
  }
}