package problems.problem1382

import datastructures.TreeNode

class Solution {
  fun balanceBST(root: TreeNode?): TreeNode? {
    if (root == null) {
      return null
    }

    val nodes = getNodes(root).sortedBy { it.`val` }

    fun recurse(i: Int, j: Int): TreeNode? {
      if (i > j) {
        return null
      }
      val mid = (i + j) / 2
      val node = nodes[mid]
      node.left = recurse(i, mid - 1)
      node.right = recurse(mid + 1, j)
      return node
    }
    return recurse(0, nodes.size - 1)
  }

  private fun getNodes(root: TreeNode?): List<TreeNode> {
    val nodes = mutableListOf<TreeNode>()

    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      recurse(node.left)
      recurse(node.right)
      nodes.add(node)
    }
    recurse(root)
    return nodes
  }
}