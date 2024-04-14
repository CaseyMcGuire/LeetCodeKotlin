package problems.problem0404

import datastructures.java.TreeNode

class Solution {
  fun sumOfLeftLeaves(root: TreeNode?): Int {
    var total = 0
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      if (node.left?.isLeaf() == true) {
        total += node.left!!.`val`
      }
      else {
        recurse(node.left)
      }
      recurse(node.right)
    }
    recurse(root)
    return total
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}