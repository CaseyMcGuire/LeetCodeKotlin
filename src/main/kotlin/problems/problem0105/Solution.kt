package problems.problem0105

import datastructures.TreeNode

class Solution {
  fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    var preorderIndex = 0
    fun recurse(inorderMin: Int, inorderMax: Int): TreeNode?  {
      if (inorderMin > inorderMax) {
        return null
      }
      val elem = preorder[preorderIndex]
      preorderIndex++
      val node = TreeNode(elem)
      if (inorderMin == inorderMax) {
        return node
      }
      val inorderIndex = inorder.indexOf(elem)
      node.left = recurse(inorderMin, inorderIndex - 1)
      node.right = recurse(inorderIndex + 1, inorderMax)
      return node
    }
    return recurse(0, preorder.size - 1)
  }
}