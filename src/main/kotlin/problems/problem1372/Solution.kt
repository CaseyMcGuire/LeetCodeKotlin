package problems.problem1372

import datastructures.TreeNode

class Solution {
  fun longestZigZag(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }

    val rightVisited = mutableSetOf<TreeNode>()
    val leftVisited = mutableSetOf<TreeNode>()

    var longestPathSoFar = 0

    fun recurse(node: TreeNode?, pathLength: Int, direction: Direction) {
      if (node == null) {
        return
      }

      if (pathLength > longestPathSoFar) {
        longestPathSoFar = pathLength
      }

      if (direction == Direction.LEFT && !leftVisited.add(node)) {
        return
      }
      if (direction == Direction.RIGHT && !rightVisited.add(node)) {
        return
      }

      when (direction) {
        Direction.START -> {
          recurse(node.left, 1, Direction.LEFT)
          recurse(node.right, 1, Direction.RIGHT)
        }
        Direction.LEFT -> recurse(node.right, pathLength + 1, Direction.RIGHT)
        Direction.RIGHT -> recurse(node.left, pathLength + 1, Direction.LEFT)
      }

      recurse(node.left, 1, Direction.LEFT)
      recurse(node.right, 1, Direction.RIGHT)
    }
    recurse(root, 0, Direction.START)
    return longestPathSoFar
  }
}

enum class Direction {
  START,
  LEFT,
  RIGHT
}