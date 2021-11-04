package problems.problem0285

import datastructures.TreeNode

class Solution {
  fun inorderSuccessor(root: TreeNode?, p: TreeNode?): TreeNode? {
    if (root == null || p == null) {
      return null
    }
    val value = p.`val`
    var nextNode: TreeNode? = null
    fun recurse(node: TreeNode?) {
      if (node == null) {
        return
      }
      if (node.`val` > value) {
        if (nextNode == null) {
          nextNode = node
        }
        else if (nextNode!!.`val` > node.`val`) {
          nextNode = node
        }
      }
      recurse(node.right)
      recurse(node.left)
    }
    recurse(root)
    return nextNode
  }
}