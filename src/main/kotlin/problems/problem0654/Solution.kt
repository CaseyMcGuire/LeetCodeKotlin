package problems.problem0654

import datastructures.TreeNode

class Solution {
  fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
    if (nums.isEmpty()) {
      return null
    }

    fun buildTree(start: Int, end: Int): TreeNode? {
      if (start > end) {
        return null
      }
      val maxIndex = nums.getMaxIndexBetween(start, end)
      val cur = TreeNode(nums[maxIndex])
      cur.left = buildTree(start, maxIndex - 1)
      cur.right = buildTree(maxIndex + 1, end)
      return cur
    }
    return buildTree(0, nums.size - 1)
  }

  private fun IntArray.getMaxIndexBetween(start: Int, end: Int): Int {
    if (start == end) {
      return start
    }
    var maxSoFar = this[start]
    var maxIndex = start
    for (i in start..end) {
      if (this[i] > maxSoFar) {
        maxSoFar = this[i]
        maxIndex = i
      }
    }
    return maxIndex
  }
}