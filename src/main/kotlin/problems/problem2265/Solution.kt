package problems.problem2265

import datastructures.java.TreeNode

class Solution {
  fun averageOfSubtree(root: TreeNode?): Int {
    var numSubtrees = 0
    fun recurse(node: TreeNode?): Result {
      if (node == null) {
        return Result(0, 0L)
      }
      val leftResult = recurse(node.left)
      val rightResult = recurse(node.right)
      val totalNodes = leftResult.numNodes + rightResult.numNodes + 1
      val total = leftResult.total + rightResult.total + node.`val`.toLong()
      val average = total / totalNodes
      if (average == node.`val`.toLong()) {
        numSubtrees++
      }
      return Result(totalNodes, total)
    }
    recurse(root)
    return numSubtrees
  }
}

data class Result(val numNodes: Int, val total: Long)