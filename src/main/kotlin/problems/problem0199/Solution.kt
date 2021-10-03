package problems.problem0199

import datastructures.TreeNode

class Solution {
  fun rightSideView(root: TreeNode?): List<Int> {
    val rightMostValue = mutableListOf<Int>()
    fun recurse(current: TreeNode?, level: Int) {
      if (current == null) {
        return
      }
      if (rightMostValue.size == level) {
        rightMostValue.add(current.`val`)
      }
      recurse(current.right, level + 1)
      recurse(current.left, level + 1)
    }
    recurse(root, 0)
    return rightMostValue
  }
}