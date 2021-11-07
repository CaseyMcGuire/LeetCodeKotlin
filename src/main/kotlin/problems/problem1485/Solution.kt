package problems.problem1485

class Node(var `val`: Int) {
  var left: Node? = null
  var right: Node? = null
  var random: Node? = null
}

typealias NodeCopy = Node

class Solution {
  fun copyRandomBinaryTree(root: Node?): NodeCopy? {
    if (root == null) {
      return null
    }
    val randomNodes = mutableSetOf<Node>()
    val oldToNew = mutableMapOf<Node, NodeCopy>()
    fun recurse(node: Node): NodeCopy {
      val newNode = NodeCopy(node.`val`)
      oldToNew[node] = newNode
      if (node.random != null) {
        randomNodes.add(node)
      }
      val right = node.right
      if (right != null) {
        newNode.right = recurse(right)
      }

      val left = node.left
      if (left != null) {
        newNode.left = recurse(left)
      }

      return newNode
    }
    recurse(root)

    for (randomNode in randomNodes) {
      val new = oldToNew[randomNode]!!
      val random = randomNode.random
      val newRandom = oldToNew[random]!!
      new.random = newRandom
    }

    return oldToNew[root]!!
  }
}