package problems.problem0107

import datastructures.TreeNode

class Solution {
  fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    val levels = mutableListOf<MutableList<Int>>()
    fun recurse(node: TreeNode?, level: Int) {
      if (node == null) {
        return
      }
      if (levels.size == level) {
        levels.add(mutableListOf())
      }
      val curLevel = levels[level]
      curLevel.add(node.`val`)
      recurse(node.left, level + 1)
      recurse(node.right, level + 1)
    }
    recurse(root, 0)
    return levels.reversed()
  }
}