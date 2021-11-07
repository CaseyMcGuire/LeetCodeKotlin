package problems.problem0742

import datastructures.TreeNode
import java.util.*

class Solution {
  fun findClosestLeaf(root: TreeNode?, k: Int): Int {
    if (root == null) {
      return -1
    }
    val graph = mutableMapOf<Int, MutableSet<TreeNode>>()
    var target: TreeNode? = null
    fun recurse(parent: TreeNode?, node: TreeNode) {
      val edges = mutableSetOf<TreeNode>()
      if (parent != null) {
        edges.add(parent)
      }

      val left = node.left
      if (left != null) {
        edges.add(left)
        recurse(node, left)
      }

      val right = node.right
      if (right != null) {
        edges.add(right)
        recurse(node, right)
      }

      if (node.`val` == k) {
        target = node
      }

      graph[node.`val`] = edges
    }
    recurse(null, root)

    val visited = mutableSetOf<Int>()
    val queue = LinkedList<TreeNode>()
    queue.addLast(target)
    while (true) {
      val cur = queue.removeFirst()
      if (cur.left == null && cur.right == null) {
        return cur.`val`
      }

      visited.add(cur.`val`)
      val edges = graph[cur.`val`]!!.filter { !visited.contains(it.`val`) }
      edges.forEach { queue.addLast(it) }
    }

  }
}