package problems.problem0144

import datastructures.TreeNode

class Solution {
  fun preorderTraversal(root: TreeNode?): List<Int> {
    val elements = mutableListOf<Int>()

    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }

      elements.add(node.`val`)
      recurse(node.left)
      recurse(node.right)
    }
    recurse(root)
    return elements
  }
}