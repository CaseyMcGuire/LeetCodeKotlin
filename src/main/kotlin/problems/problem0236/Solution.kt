package problems.problem0236

import datastructures.TreeNode
import java.util.*

class Solution {
  fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (p == null || q == null) {
      return null
    }
    val nodeToParent = mutableMapOf<TreeNode, TreeNode>()
    val deque = ArrayDeque<TreeNode>()
    deque.push(root)
    while (deque.isNotEmpty()) {
      val currentNode = deque.pop()
      val left = currentNode.left
      if (left != null) {
        nodeToParent[left] = currentNode
        deque.push(left)
      }

      val right = currentNode.right
      if (right != null) {
        nodeToParent[right] = currentNode
        deque.push(right)
      }
    }
    val pathToRoot = mutableSetOf<TreeNode>()
    var currentNode = p
    while (currentNode != null) {
      pathToRoot.add(currentNode)
      currentNode = nodeToParent[currentNode]
    }

    currentNode = q
    while (currentNode != null) {
      if (pathToRoot.contains(currentNode)) {
        return currentNode
      }
      currentNode = nodeToParent[currentNode]
    }
    return root
  }
}