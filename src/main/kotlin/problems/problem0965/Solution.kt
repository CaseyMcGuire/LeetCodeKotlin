package problems.problem0965

import datastructures.TreeNode

class Solution {
  fun isUnivalTree(root: TreeNode?): Boolean {
    if (root == null) {
      return true
    }
    val value = root.`val`
    return recurse(value, root)
  }

  fun recurse(value: Int, node: TreeNode?): Boolean {
    if (node == null) {
      return true
    }
    if (node.`val` != value) {
      return false
    }
    return recurse(value, node.right) && recurse(value, node.left)
  }
}