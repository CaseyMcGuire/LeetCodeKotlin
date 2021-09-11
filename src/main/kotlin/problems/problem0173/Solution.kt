package problems.problem0173

import datastructures.TreeNode
import java.util.*

class BSTIterator(root: TreeNode?) {

  val stack = ArrayDeque<TreeNode>()

  init {
    insertNodeAndLeftChildren(root)
  }

  fun next(): Int {
    val node = stack.pop()
    insertNodeAndLeftChildren(node.right)
    return node.`val`
  }

  fun hasNext(): Boolean {
    return stack.isNotEmpty()
  }


  private fun insertNodeAndLeftChildren(root: TreeNode?) {
    if (root == null) {
      return
    }
    stack.push(root)
    var curr = root.left
    while (curr != null) {
      stack.push(curr)
      curr = curr.left
    }
  }

}