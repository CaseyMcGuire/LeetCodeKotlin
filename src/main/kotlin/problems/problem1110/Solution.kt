package problems.problem1110

import datastructures.TreeNode

class Solution {
  fun delNodes(root: TreeNode?, toDelete: IntArray): List<TreeNode?> {
    val nodesToDelete = toDelete.toHashSet()
    val roots = mutableListOf<TreeNode>()
    fun recurse(node: TreeNode?, parentWasDeleted: Boolean) {
      if (node == null) {
        return
      }
      val shouldDelete = nodesToDelete.contains(node.`val`)
      if (!shouldDelete && parentWasDeleted) {
        roots.add(node)
      }
      val left = node.left
      if (left != null && nodesToDelete.contains(left.`val`)) {
        node.left = null
      }
      val right = node.right
      if (right != null && nodesToDelete.contains(right.`val`)) {
        node.right = null
      }
      recurse(left, shouldDelete)
      recurse(right, shouldDelete)
    }
    recurse(root, true)
    return roots
  }
}