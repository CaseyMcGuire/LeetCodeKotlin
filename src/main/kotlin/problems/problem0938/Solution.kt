package problems.problem0938

import datastructures.TreeNode

class Solution {
  fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    var sum = 0
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      val value = node.`val`
      if (value in low..high) {
        sum += value
      }
      recurse(node.left)
      recurse(node.right)
    }
    recurse(root)
    return sum
  }
}