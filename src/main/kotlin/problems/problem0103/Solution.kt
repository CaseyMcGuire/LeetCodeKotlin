package problems.problem0103

import datastructures.TreeNode

class Solution {
  fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) {
      return emptyList()
    }
    val levels = mutableListOf<List<Int>>()
    var currentLevel = mutableListOf<TreeNode>(root)
    var nextLevel = mutableListOf<TreeNode>()
    var direction = Direction.RIGHT
    while (currentLevel.isNotEmpty()) {
      val nums = currentLevel.map { it.`val` }
      if (direction == Direction.RIGHT) {
        levels.add(nums)
      }
      else {
        levels.add(nums.reversed())
      }

      for (node in currentLevel) {
        val left = node.left
        if (left != null) {
          nextLevel.add(left)
        }
        val right = node.right
        if (right != null) {
          nextLevel.add(right)
        }
      }
      direction = if (direction == Direction.RIGHT) Direction.LEFT else Direction.RIGHT
      currentLevel = nextLevel
      nextLevel = mutableListOf<TreeNode>()
    }
    return levels
  }
}

enum class Direction {
  LEFT,
  RIGHT
}