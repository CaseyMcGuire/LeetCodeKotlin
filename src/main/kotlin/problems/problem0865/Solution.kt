package problems.problem0865

import datastructures.TreeNode

class Solution {
  fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? {
    if (root == null) {
      return null
    }
    val nodeToParent = mutableMapOf<TreeNode, TreeNode?>()
    var deepestDepth = 0
    var deepestNodes = mutableListOf<TreeNode>()

    fun recurse(node: TreeNode?, parent: TreeNode?, depth: Int) {
      if (node == null) {
        return
      }
      if (depth > deepestDepth) {
        deepestDepth = depth
        deepestNodes = mutableListOf<TreeNode>(node)
      }
      else if (depth == deepestDepth) {
        deepestNodes.add(node)
      }
      nodeToParent[node] = parent
      recurse(node.left, node, depth + 1)
      recurse(node.right, node, depth + 1)
    }
    recurse(root, null, 1)
    if (deepestNodes.isEmpty() || deepestDepth == 1) {
      return root
    }
    else if (deepestNodes.size == 1) {
      return deepestNodes[0]
    }
    val paths = deepestNodes.map { nodeToParent.getPathToRoot(it) }
    for (i in (deepestDepth - 1) downTo 0) {
      val nodeSet = paths.map { it[i] }.toSet()
      if (nodeSet.size > 1) {
        return paths[0][i + 1]
      }
    }
    return root
  }

  private fun Map<TreeNode, TreeNode?>.getPathToRoot(node: TreeNode): List<TreeNode> {
    val path = mutableListOf<TreeNode>()
    var cur: TreeNode? = node
    while (cur != null) {
      path.add(cur)
      cur = this[cur]
    }
    return path
  }


}