package problems.problem0987

import datastructures.TreeNode

class Solution {
  fun verticalTraversal(root: TreeNode?): List<List<Int>> {
    val verticalOrder = mutableListOf<Level>()

    fun recurse(node: TreeNode?, row: Int, column: Int) {
      if (node == null) {
        return
      }
      verticalOrder.add(Level( row, column, node.`val`))
      recurse(node.left, row + 1, column - 1)
      recurse(node.right, row + 1, column + 1)
    }
    recurse(root, 0, 0)

    return verticalOrder.groupBy { it.column }
      .entries
      .sortedWith(compareBy({it.key}))
      .map { it.value }
      .map { it.sortedWith(compareBy({it.row}, {it.value})) }
      .map { it.map { it.value } }
  }
}

data class Level(val row: Int , val column: Int, val value: Int)