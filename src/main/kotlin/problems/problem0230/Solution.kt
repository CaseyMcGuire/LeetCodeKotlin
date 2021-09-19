package problems.problem0230

import datastructures.TreeNode
import java.util.*


class Solution {
  fun kthSmallest(root: TreeNode?, k: Int): Int {
    if (root == null) {
      return -1
    }
    val pq = PriorityQueue<Int>(compareBy{ it })
    val stack = ArrayDeque<TreeNode>()
    stack.push(root)
    while (stack.isNotEmpty()) {
      val currentNode = stack.pop()
      pq.add(currentNode.`val`)
      val left = currentNode.left
      val right = currentNode.right
      if (left != null) {
        stack.push(left)
      }
      if (right != null) {
        stack.push(right)
      }
    }
    if (pq.size < k) {
      return -1
    }
    for (i in 0 until k - 1) {
      pq.poll()
    }
    return pq.poll()
  }
}