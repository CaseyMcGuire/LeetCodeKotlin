package problems.problem0530

import datastructures.TreeNode

class Solution {
  fun getMinimumDifference(root: TreeNode?): Int {
    if (root == null) {
      return -1
    }
    var prevValue: Int? = null
    var maxSoFar: Int? = null
    fun recurse(node: TreeNode) {

      val left = node.left
      if (left != null) {
        recurse(left)
      }

      if (prevValue != null) {
        val difference = Math.abs(node.`val` - prevValue!!)
        if (maxSoFar == null || maxSoFar!! > difference) {
          maxSoFar = difference
        }
      }
      prevValue = node.`val`

      val right = node.right
      if (right != null) {
        recurse(right)
      }
    }
    recurse(root)
    return maxSoFar!!
  }
}