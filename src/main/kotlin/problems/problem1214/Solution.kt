package problems.problem1214

import datastructures.TreeNode

class Solution {
  fun twoSumBSTs(root1: TreeNode?, root2: TreeNode?, target: Int): Boolean {
    val set = mutableSetOf<Int>()
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      set.add(node.`val`)
      recurse(node.left)
      recurse(node.right)
    }
    recurse(root1)

    fun findSum(node: TreeNode?): Boolean {
      if (node == null) {
        return false
      }
      val remainder = target - node.`val`
      if (set.contains(remainder)) {
        return true
      }
      return findSum(node.left) || findSum(node.right)
    }
    return findSum(root2)
  }
}