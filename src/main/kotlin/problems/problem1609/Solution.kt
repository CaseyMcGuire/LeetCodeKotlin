package problems.problem1609

import datastructures.TreeNode

class Solution {
  fun isEvenOddTree(root: TreeNode?): Boolean {

    val levelToValues = mutableMapOf<Int, MutableList<Int>>()
    fun recurse(node: TreeNode?, level: Int) {
      if (node == null) {
        return
      }
      recurse(node.left, level + 1)
      val values = levelToValues[level] ?: mutableListOf()
      values.add(node.`val`)
      levelToValues[level] = values
      recurse(node.right, level + 1)
    }
    recurse(root, 0)

    for (entry in levelToValues.entries) {
      val level = entry.key
      val (levelValues, remainder) = if (level % 2 == 0) {
        Pair(entry.value, 1)
      }
      else {
        Pair(entry.value.reversed(), 0)
      }

      if (levelValues != entry.value.sorted() ||
        levelValues.toSet().size != levelValues.size ||
        !levelValues.all { it % 2 == remainder }) {
        return false
      }
    }

    return true
  }
}