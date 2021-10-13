package problems.problem0113

import datastructures.TreeNode

class Solution {
  fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val paths = mutableListOf<List<Int>>()
    fun recurse(node: TreeNode?, curPath: MutableList<Int>, curSum: Int) {
      if (node == null) {
        return
      }
      curPath.add(node.`val`)
      val newSum = curSum + node.`val`
      if (node.left == null && node.right == null) {
        if (newSum == targetSum) {
          paths.add(curPath.toList())
        }
      }
      else {
        recurse(node.left, curPath, newSum)
        recurse(node.right, curPath, newSum)
      }
      curPath.removeAt(curPath.size - 1)
    }
    recurse(root, mutableListOf<Int>(), 0)
    return paths
  }
}