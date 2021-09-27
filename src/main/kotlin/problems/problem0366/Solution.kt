package problems.problem0366

import datastructures.TreeNode
import java.util.*

class Solution {
  fun findLeaves(root: TreeNode?): List<List<Int>> {
    if (root == null) {
      return emptyList()
    }

    val stack = ArrayDeque<Node>()
    val nodeToParent = mutableMapOf<TreeNode, Node>()
    var leaves = mutableListOf<Node>()
    stack.push(Node(root, 0))
    while (stack.isNotEmpty()) {
      val current = stack.pop()
      val currentNode = current.node
      if (currentNode.left == null && currentNode.right == null) {
        leaves.add(current)
        continue
      }
      val left = currentNode.left
      if (left != null) {
        val node = Node(left, current.level + 1)
        nodeToParent[left] = current
        stack.push(node)
      }

      val right = currentNode.right
      if (right != null) {
        val node = Node(right, current.level + 1)
        nodeToParent[right] = current
        stack.push(node)
      }
    }

    val allLeaves = mutableListOf<List<Int>>()
    var nextLeaves = mutableListOf<Node>()
    while (leaves.isNotEmpty()) {
      val currentLeaves = leaves.sortedByDescending { it.level }.map { it.node.`val` }
      allLeaves.add(currentLeaves)

      for (leaf in leaves) {
        val parent = nodeToParent[leaf.node]
        if (parent == null) {
          break
        }
        val parentNode = parent.node
        if (parentNode.left === leaf.node) {
          parentNode.left = null
        }
        else {
          parentNode.right = null
        }

        if (parentNode.isLeaf()) {
          nextLeaves.add(parent)
        }
        println(parentNode.`val`)
      }
      leaves = nextLeaves
      nextLeaves = mutableListOf<Node>()
    }
    return allLeaves
  }

  private fun TreeNode.isLeaf(): Boolean {
    return this.left == null && this.right == null
  }
}

data class Node(val node: TreeNode, val level: Int)