package problems.problem0222

import datastructures.TreeNode
import kotlin.math.pow

class Solution {
  fun countNodes(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    if (root.left == null && root.right == null) {
      return 1
    }
    val rightDepth = findRightDepth(root)
    var leftDepth = findLeftDepth(root)
    // we have a full tree
    if (rightDepth == leftDepth) {
      return getFullTreeHeight(rightDepth)
    }

    return countNodes(root.right) + countNodes(root.left) + 1
  }

  private fun getFullTreeHeight(height: Int): Int {
    return (2.0.pow(height + 1) - 1).toInt()
  }

  private fun findRightDepth(root: TreeNode?): Int {
    var height = 0
    var currentNode = root?.right
    while (currentNode != null) {
      height++
      currentNode = currentNode.right
    }
    return height
  }

  private fun findLeftDepth(root: TreeNode?): Int {
    var height = 0
    var currentNode = root?.left
    while (currentNode != null) {
      height++
      currentNode = currentNode.left
    }
    return height
  }


}
fun main(args: Array<String>) {
  val root = TreeNode(1,
    TreeNode(2,
      TreeNode(4),
      TreeNode(5)
    ),
    TreeNode(3,
      TreeNode(6)
    )
  )
  val root2 = TreeNode(1, TreeNode(2))
  val root3 = TreeNode(1,
    TreeNode(2,
      TreeNode(4),
      TreeNode(5)
    ),
    TreeNode(3)
  )
  val root4 = TreeNode(1,
    TreeNode(2,
      TreeNode(4)
    ),
    TreeNode(3)

  )
  println(Solution().countNodes(root4))
}