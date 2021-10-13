package problems.problem0108

import datastructures.TreeNode

class Solution {
  fun sortedArrayToBST(nums: IntArray): TreeNode? {

    fun recurse(start: Int, end: Int): TreeNode? {
      if (start > end) {
        return null
      }
      val mid = (start + end) / 2
      val curNode = TreeNode(nums[mid])
      curNode.left = recurse(start, mid - 1)
      curNode.right = recurse(mid + 1, end)
      return curNode
    }
    return recurse(0, nums.size - 1)
  }
}