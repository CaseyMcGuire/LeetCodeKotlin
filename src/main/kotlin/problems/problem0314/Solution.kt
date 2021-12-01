package problems.problem0314

import datastructures.TreeNode

class Solution {
  fun verticalOrder(root: TreeNode?): List<List<Int>> {
    val columnToNode = mutableMapOf<Int, MutableList<Node>>()

    fun dfs(node: TreeNode?, row: Int, column: Int) {
      if (node == null) {
        return
      }
      val curNode = Node(row, node.`val`)
      val nodes = columnToNode[column] ?: mutableListOf()
      nodes.add(curNode)
      columnToNode[column] = nodes
      dfs(node.left, row + 1, column - 1)
      dfs(node.right, row + 1, column + 1)
    }
    dfs(root, 0, 0)
    val nodesSortedByColumn = columnToNode.entries.sortedBy { it.key }.map { it.value }
    val nodesSortedByRow = nodesSortedByColumn.map { it.sortedWith(compareBy({ it.row })) }
    return nodesSortedByRow.map { elem -> elem.map { it.value } }
  }
}

data class Node(val row: Int, val value: Int)