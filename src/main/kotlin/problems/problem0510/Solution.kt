package problems.problem0510

import datastructures.TreeNode

class Solution {
  fun inorderSuccessor(node: Node?): Node? {
    if (node == null) {
      return null
    }
    val right = node.right
    if (right != null) {
      var curNode = right
      while (curNode?.left != null) {
        curNode = curNode.left
      }
      return curNode
    }

    var curNode = node
    while (curNode != null && curNode.`val` <= node.`val`) {
      curNode = curNode.parent
    }
    return curNode

  }
}
 class Node(var `val`: Int) {
   var left: TreeNode? = null
   var right: TreeNode? = null
   var parent: Node? = null
 }