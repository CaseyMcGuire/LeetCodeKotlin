package problems.problem0437

import datastructures.TreeNode

class Solution {
  fun pathSum(root: TreeNode?, targetSum: Int): Int {
    if (root == null) {
      return 0
    }
    var numPaths = 0
    val targetSumLong = targetSum.toLong()
    fun recurse(curNode: TreeNode?): List<Long> {
      if (curNode == null) {
        return emptyList()
      }

      if (curNode.`val` == targetSum) {
        numPaths++
      }

      val leftPathSums = recurse(curNode.left)
      val rightPathSums = recurse(curNode.right)
      val next = mutableListOf<Long>(curNode.`val`.toLong())
      for (num in leftPathSums) {
        val sum = num + curNode.`val`
        if (sum == targetSumLong) {
          numPaths++
        }
        next.add(sum)
      }

      for (num in rightPathSums) {
        val sum = num + curNode.`val`
        if (sum == targetSumLong) {
          numPaths++
        }
        next.add(sum)
      }
      return next
    }
    recurse(root)
    return numPaths
  }
}