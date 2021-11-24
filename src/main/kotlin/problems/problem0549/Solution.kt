package problems.problem0549

import datastructures.TreeNode

class Solution {
  fun longestConsecutive(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }

    val graph = mutableMapOf<TreeNode, MutableSet<TreeNode>>()

    fun recurse(node: TreeNode, parent: TreeNode?) {

      val set = mutableSetOf<TreeNode>()

      if (parent != null) {
        set.add(parent)
      }
      if (node.left != null) {
        val left = node.left!!
        recurse(left, node)
        set.add(node.left!!)
      }
      if (node.right != null) {
        val right = node.right!!
        recurse(right, node)
        set.add(right)
      }

      graph[node] = set
    }

    recurse(root, null)

    val pathLengths = mutableMapOf<TreeNode, Int>()
    fun dfs(node: TreeNode): Int {
      val existing = pathLengths[node]
      if (existing != null) {
        return existing
      }
      val neighbors = graph[node]!!.filter { it.`val` == node.`val` - 1 }
      if (neighbors.isEmpty()) {
        return 1
      }
      val longestPath = 1 + neighbors.map {dfs(it)}.max()!!
      pathLengths[node] = longestPath
      return longestPath
    }

    var largestSoFar = Integer.MIN_VALUE
    for (node in graph.keys) {
      val value = dfs(node)
      if (largestSoFar < value) {
        val foo = node.`val`
        largestSoFar = value
      }
    }
    return largestSoFar

  }
}

enum class Direction {
  UP,
  DOWN
}