package problems.problem1302

import datastructures.TreeNode

class Solution {
  fun deepestLeavesSum(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }

    var deepest: DeepestNode? = null
    fun recurse(node: TreeNode?, level: Int) {
      if (node == null) {
        return
      }
      if (node.isLeaf()) {
        if (deepest == null || deepest!!.level < level) {
          deepest = DeepestNode(level, node.`val`)
        }
        else if (deepest!!.level == level) {
          deepest!!.sum += node.`val`
        }
        return
      }

      recurse(node.left, level + 1)
      recurse(node.right, level + 1)
    }
    recurse(root, 0)
    return deepest!!.sum
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.right == null && this.left == null
  }
}

data class DeepestNode(val level: Int, var sum: Int)