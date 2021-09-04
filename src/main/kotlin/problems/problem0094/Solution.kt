package problems.problem0094

import datastructures.TreeNode

class Solution {
  fun inorderTraversal(root: TreeNode?): List<Int> {
    val inorder = mutableListOf<Int>()
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      if (node.right == null && node.left == null) {
        inorder.add(node.`val`)
        return
      }
      recurse(node.left)
      inorder.add(node.`val`)
      recurse(node.right)
    }
    recurse(root)
    return inorder
  }
}