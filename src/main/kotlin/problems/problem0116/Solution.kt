package problems.problem0116

class Solution {
  fun connect(root: Node?): Node? {
    if (root == null) {
      return null
    }
    fun recurse(node: Node?) {
      if (node == null) {
        return
      }
      node.left?.next = node.right
      node.right?.next = node?.next?.left
      recurse(node.left)
      recurse(node.right)
    }
    recurse(root)
    return root
  }
}

class Node(var `val`: Int) {
  var left: Node? = null
  var right: Node? = null
  var next: Node? = null
}