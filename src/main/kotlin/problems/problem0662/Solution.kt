package problems.problem0662

import datastructures.TreeNode

class Solution {
  fun widthOfBinaryTree(root: TreeNode?): Int {
    var maxSoFar = 0
    val leftMostIndex = mutableMapOf<Int, Long>()
    val rightMostIndex = mutableMapOf<Int, Long>()

    fun recurse(node: TreeNode?, index: Long, level: Int) {
      if (node == null) {
        return
      }
      val value = node.`val`
      val leftMostIndexForLevel = leftMostIndex[level]
      if (leftMostIndexForLevel == null || leftMostIndexForLevel > index) {
        leftMostIndex[level] = index
      }

      val rightMostIndexForLevel = rightMostIndex[level]
      if (rightMostIndexForLevel == null || rightMostIndexForLevel < index) {
        rightMostIndex[level] = index
      }

      val leftChildIndex = index * 2L
      val rightChildIndex = index * 2L + 1L
      recurse(node?.left, leftChildIndex, level + 1)
      recurse(node?.right, rightChildIndex, level + 1)
    }
    recurse(root, 0, 0)

    var curLevel = 0
    var curMax = 0L
    while (true) {
      val leftMost = leftMostIndex[curLevel]
      val rightMost = rightMostIndex[curLevel]
      if (leftMost == null || rightMost == null) {
        return curMax.toInt()
      }
      curMax = Math.max(curMax, (rightMost - leftMost + 1).toLong())
      curLevel++
    }
    return curMax.toInt()
  }
}