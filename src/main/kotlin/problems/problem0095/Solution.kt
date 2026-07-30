package problems.problem0095

import datastructures.TreeNode

class Solution {
  fun generateTrees(n: Int): List<TreeNode?> {
    val nums = (1..n).toList()

    fun recurse(startIndex: Int, endIndex: Int): List<TreeNode> {
      if (startIndex == endIndex) {
        return listOf(TreeNode(nums[startIndex]))
      }
      if (endIndex < startIndex) {
        return emptyList()
      }

      val trees = mutableListOf<TreeNode>()
      for (i in startIndex..endIndex) {

        val leftSubtrees = recurse(startIndex, i - 1)
        val rightSubtrees = recurse(i + 1, endIndex)
        if (leftSubtrees.isEmpty()) {
          for (subtree in rightSubtrees) {
            val root = TreeNode(nums[i])
            root.right = subtree
            trees.add(root)
          }
        }
        else if (rightSubtrees.isEmpty()) {
          for (subtree in leftSubtrees) {
            val root = TreeNode(nums[i])
            root.left = subtree
            trees.add(root)
          }
        }
        else {
          for (left in leftSubtrees) {
            for (right in rightSubtrees) {
              val root = TreeNode(nums[i])
              root.left = left
              root.right = right
              trees.add(root)
            }
          }
        }
      }
      return trees
    }
    return recurse(0, n - 1)
  }
}