package problems.problem1028

import datastructures.TreeNode

class Solution {
  fun recoverFromPreorder(traversal: String): TreeNode? {
    if (traversal.isEmpty()) {
      return null
    }
    val lastNodeSeenAtDepth = mutableListOf<TreeNode>()
    val tokens = getTokens(traversal)
    for (token in tokens) {
      // found the root
      if (token.depth == 0) {
        lastNodeSeenAtDepth.add(TreeNode(token.value))
        continue
      }
      val currentNode = TreeNode(token.value)
      val parent = lastNodeSeenAtDepth[token.depth - 1]
      if (parent.left == null) {
        parent.left = currentNode
      }
      else {
        parent.right = currentNode
      }
      if (lastNodeSeenAtDepth.size <= token.depth) {
        lastNodeSeenAtDepth.add(currentNode)
      }
      else {
        lastNodeSeenAtDepth[token.depth] = currentNode
      }
    }
    return lastNodeSeenAtDepth[0]
  }

  private fun getTokens(traversal: String): MutableList<NodeToken> {
    val tokens = mutableListOf<NodeToken>()
    var i = 0
    while (i < traversal.length) {

      var depth = 0
      while (i < traversal.length && traversal[i] == '-') {
        depth++
        i++
      }

      val num = StringBuilder()
      while (i < traversal.length && traversal[i] != '-') {
        num.append(traversal[i])
        i++
      }

      tokens.add(NodeToken(num.toString().toInt(), depth))
    }
    return tokens
  }

}

data class NodeToken(val value: Int, val depth: Int)