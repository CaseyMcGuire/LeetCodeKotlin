package problems.problem1650

import datastructures.TreeNode

class Solution {
  fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
    if (p == null) {
      return null
    }
    if (q == null) {
      return null
    }
    val pPathToRoot = mutableSetOf<Int>()
    var currentNode = p
    while (currentNode != null) {
      if (p.`val` == q.`val`) {
        return q
      }
      pPathToRoot.add(currentNode.`val`)
      currentNode = currentNode.parent
    }

    currentNode = q
    while (currentNode != null) {
      if (pPathToRoot.contains(currentNode.`val`)) {
        return currentNode
      }
      currentNode = currentNode.parent
    }
    return null;
  }
}

sealed class Node(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
  var parent: Node? = null
}