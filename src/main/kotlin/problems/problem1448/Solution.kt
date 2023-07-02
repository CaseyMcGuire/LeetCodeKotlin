package problems.problem1448

import datastructures.TreeNode

class Solution {
  fun goodNodes(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    var numGoodNodes = 0

    fun recurse(node: TreeNode?, maxSoFar: Int) {
      if (node == null) {
        return
      }
      if (node.`val` >= maxSoFar) {
        numGoodNodes++
      }
      val newMax = Math.max(node.`val`, maxSoFar)
      recurse(node.left, newMax)
      recurse(node.right, newMax)
    }
    recurse(root, root.`val`)
    return numGoodNodes
  }
}