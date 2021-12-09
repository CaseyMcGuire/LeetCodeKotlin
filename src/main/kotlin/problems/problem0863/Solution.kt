package problems.problem0863

import datastructures.TreeNode
import java.util.*

class Solution {
  fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
    if (root == null || target == null) {
      return emptyList()
    }
    val pathToTarget = getPathToTarget(root, target.`val`)
    val nodesOfKDistance = mutableListOf<Int>()
    getNodesOfKDistance(target, k, nodesOfKDistance)

    var curK = k - 1
    var previousNode = pathToTarget.pop()

    while (curK >= 0 && pathToTarget.isNotEmpty()) {
      val curNode = pathToTarget.pop()
      if (curK == 0) {
        nodesOfKDistance.add(curNode.`val`)
        break
      }
      if (curNode.left?.`val` == previousNode.`val`) {
        getNodesOfKDistance(curNode.right, curK - 1, nodesOfKDistance)
      }
      else {
        getNodesOfKDistance(curNode.left, curK - 1, nodesOfKDistance)
      }
      previousNode = curNode
      curK--
    }
    return nodesOfKDistance
  }

  private fun getNodesOfKDistance(node: TreeNode?, k: Int, nodes: MutableList<Int>) {
    if (node == null || k < 0) {
      return
    }
    if (k == 0) {
      nodes.add(node.`val`)
      return
    }
    getNodesOfKDistance(node.right, k - 1, nodes)
    getNodesOfKDistance(node.left, k - 1, nodes)
  }

  private fun getPathToTarget(root: TreeNode, targetValue: Int): Deque<TreeNode> {
    fun recurse(node: TreeNode?, curPath: Deque<TreeNode>): Deque<TreeNode>? {
      if (node == null) {
        return null
      }
      curPath.push(node)
      if (node.`val` == targetValue) {
        return curPath
      }
      val foundLeft = recurse(node.left, curPath)
      if (foundLeft != null) {
        return foundLeft
      }

      val foundRight = recurse(node.right, curPath)
      if (foundRight != null) {
        return foundRight
      }
      curPath.pop()
      return null
    }
    return recurse(root, ArrayDeque())!!
  }
}