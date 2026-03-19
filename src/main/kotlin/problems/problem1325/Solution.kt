package problems.problem1325

import datastructures.TreeNode

class Solution {
  fun removeLeafNodes(node: TreeNode?, target: Int): TreeNode? {
    if (node == null) {
      return null
    }

    node.left = removeLeafNodes(node.left, target)
    node.right = removeLeafNodes(node.right, target)
    if (node.`val` == target && node.left == null && node.right == null) {
      return null
    }
    return node
  }
}