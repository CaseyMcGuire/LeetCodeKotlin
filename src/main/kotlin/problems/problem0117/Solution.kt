package problems.problem0117

class Solution {
  fun connect(root: Node?): Node? {

    fun recurse(node: Node?, parent: Node?) {
      if (node == null) {
        return
      }
      if (node === parent?.left) {
        if (parent?.right != null) {
          node.next = parent?.right
        }
        else {
          node.next = getFirstLeftChildNode(parent?.next)
        }
      }
      else {
        node.next = getFirstLeftChildNode(parent?.next)
      }
      recurse(node.right, node)
      recurse(node.left, node)
    }
    recurse(root?.right, root)
    recurse(root?.left, root)

    return root
  }

  private fun getFirstLeftChildNode(node: Node?): Node? {
    var cur = node
    while (cur != null) {
      val left = cur.left
      if (left != null) {
        return left
      }
      val right = cur.right
      if (right != null) {
        return right
      }
      cur = cur.next
    }
    return null
  }
}

class Node(var `val`: Int) {
  var left: Node? = null
  var right: Node? = null
  var next: Node? = null
}