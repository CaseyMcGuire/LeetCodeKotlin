package problems.problem0112

import datastructures.TreeNode
import java.util.*

class Solution {
  fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    if (root == null) {
      return false
    }
    val stack = ArrayDeque<NodeAndSum>()
    stack.push(NodeAndSum(node = root, sumSoFar = root.`val`))
    while (!stack.isEmpty()) {

      val (node, sumSoFar) = stack.pop()
      val isLeaf = node.left == null && node.right == null
      if (isLeaf && sumSoFar == sum) {
        return true
      }

      val left = node.left
      if (left != null) {
        stack.push(NodeAndSum(node = left, sumSoFar = sumSoFar + left.`val`))
      }

      val right = node.right
      if (right != null) {
        stack.push(NodeAndSum(node = right, sumSoFar =  sumSoFar + right.`val`))
      }
    }

    return false
  }


  data class NodeAndSum(val node: TreeNode, val sumSoFar: Int)
}