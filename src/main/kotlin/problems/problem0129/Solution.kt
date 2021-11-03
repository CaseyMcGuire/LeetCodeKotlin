package problems.problem0129

import datastructures.TreeNode
import java.util.*

class Solution {
  fun sumNumbers(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }

    val path = LinkedList<String>()
    var totalSoFar = 0
    fun recurse(node: TreeNode) {
      path.addLast(node.`val`.toString())
      if (node.left == null && node.right == null) {
        totalSoFar += path.joinToString("").toInt()
      }
      else {
        val left = node.left
        if (left != null) {
          recurse(left)
        }
        val right = node.right
        if (right != null) {
          recurse(right)
        }
      }
      path.removeLast()
    }

    recurse(root)
    return totalSoFar
  }
}