package problems.problem0270

import datastructures.TreeNode

class Solution {
  fun closestValue(root: TreeNode?, target: Double): Int {
    if (root == null) {
      return -1
    }
    var closestSoFar: Double? = null
    fun dfs(node: TreeNode?) {
      if (node == null) {
        return
      }

      val value = node.`val`.toDouble()
      val difference = Math.abs(target - value)
      if (closestSoFar == null || Math.abs(target - closestSoFar!!) > difference) {
        closestSoFar = value
      }
      dfs(node.right)
      dfs(node.left)
    }
    dfs(root)
    return closestSoFar!!.toInt()
  }
}