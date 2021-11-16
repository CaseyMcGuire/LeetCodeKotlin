package problems.problem0114

import datastructures.TreeNode

class Solution {
  fun flatten(root: TreeNode?): Unit {
    if (root == null) {
      return
    }

    fun recurse(node: TreeNode): TreeNode {
      val left = node.left
      val right = node.right
      if (right == null && left == null) {
        return node
      }
      else if (right == null) {
        val temp = recurse(left!!)
        node.left = null
        node.right = left
        return temp
      }
      else if (left == null) {
        return recurse(right)
      }
      else {
        val leftTail = recurse(left)
        node.left = null
        node.right = left
        leftTail.right = right
        return recurse(right)
      }
    }

    recurse(root)
  }
}