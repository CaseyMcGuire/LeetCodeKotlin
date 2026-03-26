package problems.problem0662

import datastructures.TreeNode

class Solution {
  fun widthOfBinaryTree(root: TreeNode?): Int {
    val levelMin = mutableMapOf<Int, Int>()
    val levelMax = mutableMapOf<Int, Int>()
    var maxSoFar = 0
    fun traverse(node: TreeNode?, level: Int, index: Int) {
      if (node == null) {
        return
      }

      val newMax = levelMax.merge(level, index) { cur, acc -> Math.max(cur, acc) }!!
      val newMin = levelMin.merge(level, index) { cur, acc -> Math.min(cur, acc) }!!
      maxSoFar = Math.max(maxSoFar, Math.abs(newMax - newMin) + 1)

      traverse(node.left, level + 1, (index * 2) - 1)
      traverse(node.right, level + 1, index * 2)
    }
    traverse(root, 0, 1)
    return maxSoFar
  }
}