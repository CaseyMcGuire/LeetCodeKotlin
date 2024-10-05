package problems.problem0145

import datastructures.TreeNode

class Solution {
  fun postorderTraversal(root: TreeNode?): List<Int> {
    val elements = mutableListOf<Int>()

    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      recurse(node.left)
      recurse(node.right)
      elements.add(node.`val`)
    }
    recurse(root)
    return elements
  }
}