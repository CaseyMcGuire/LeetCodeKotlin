package problems.problem0099

import datastructures.TreeNode

class Solution {
  fun recoverTree(root: TreeNode?) {
    val nodes = mutableListOf<TreeNode>()

    fun traverse(node: TreeNode?) {
      if (node == null) {
        return
      }
      traverse(node.left)
      nodes.add(node)
      traverse(node.right)
    }
    traverse(root)

    val values = nodes.map { it.`val` }.sorted()
    for (i in nodes.indices) {
      nodes[i].`val` = values[i]
    }

  }
}