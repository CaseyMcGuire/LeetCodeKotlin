package problems.problem0958

import datastructures.TreeNode

class Solution {
  fun isCompleteTree(root: TreeNode?): Boolean {
    if (root == null) {
      return false
    }
    if (root.left == null && root.right == null) {
      return true
    }
    var isCurrentTheLastLevel = false
    var curLevel = mutableListOf(root)
    var nextLevel = mutableListOf<TreeNode>()

    while (curLevel.isNotEmpty()) {
      var foundNullChild = false
      for (node in curLevel) {
        val left = node.left
        val right = node.right
        if (isCurrentTheLastLevel || foundNullChild) {
          if (right != null || left != null) {
            return false
          }
        }
        if (right != null && left == null) {
          return false
        }
        foundNullChild = right == null || left == null

        if (left != null) {
          nextLevel.add(left)
        }

        if (right != null) {
          nextLevel.add(right)
        }
      }

      isCurrentTheLastLevel = foundNullChild
      curLevel = nextLevel
      nextLevel = mutableListOf()
    }
    return true
  }
}