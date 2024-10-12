package problems.problem0257

import datastructures.TreeNode

class Solution {
  fun binaryTreePaths(root: TreeNode?): List<String> {
    val curPath = mutableListOf<Int>()
    val paths = mutableListOf<String>()

    fun recurse(node: TreeNode) {
      if (node.left == null && node.right == null) {
        curPath.add(node.`val`)
        paths.add(curPath.joinToString("->"))
        curPath.removeLast()
        return
      }
      curPath.add(node.`val`)
      node.right?.let { recurse(it) }
      node.left?.let { recurse(it) }
      curPath.removeLast()
    }
    root?.let { recurse(it) }
    return paths
  }
}