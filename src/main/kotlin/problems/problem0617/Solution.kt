package problems.problem0617

import datastructures.TreeNode

class Solution {
  fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null && root2 == null) {
      return null
    }

    val newVal = if (root1 == null) { root2!!.`val` }
    else if (root2 == null) { root1!!.`val` }
    else { root1!!.`val` + root2!!.`val` }
    val mergedNode = TreeNode(newVal)

    mergedNode.left = mergeTrees(root1?.left, root2?.left)
    mergedNode.right = mergeTrees(root1?.right, root2?.right)
    return mergedNode
  }
}