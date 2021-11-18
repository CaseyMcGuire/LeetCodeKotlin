package problems.problem1120

import datastructures.TreeNode

class Solution {
  fun maximumAverageSubtree(root: TreeNode?): Double {
    if (root == null) {
      return 0.0
    }

    var maxSoFar = 0.0

    fun recurse(node: TreeNode?): Subtree {
      if (node == null) {
        return Subtree(0, 0.0)
      }
      if (node.isLeaf()) {
        val value = node.`val`.toDouble()
        if (maxSoFar < value) {
          maxSoFar = value
        }
        return Subtree(1, node.`val`.toDouble())
      }
      val left = recurse(node.left)
      val right = recurse(node.right)
      val numNodes = 1 + left.numNodes + right.numNodes
      val sum = left.sum + right.sum + node.`val`.toDouble()
      val average = sum / numNodes.toDouble()
      if (average > maxSoFar) {
        maxSoFar = average
      }

      return Subtree(numNodes, sum)
    }
    recurse(root)

    return maxSoFar
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}

data class Subtree(val numNodes: Int, val sum: Double)