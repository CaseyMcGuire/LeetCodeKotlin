package problems.problem0426

class Solution {
  fun treeToDoublyList(root:Node?): Node? {
    if (root == null) {
      return null
    }
    fun recurse(node: Node): HeadTail {
      val left = node.left
      val right = node.right
      if (left == null && right == null) {
        return HeadTail(node, node)
      }

      if (right == null) {
        val leftHT = recurse(left!!)
        leftHT.tail.right = node
        node.left = leftHT.tail
        return HeadTail(leftHT.head, node)
      }

      if (left == null) {
        val leftHT = recurse(right!!)
        node.right = leftHT.head
        leftHT.head.left = node
        return HeadTail(node, leftHT.tail)
      }

      val rightHT = recurse(right)
      val leftHT = recurse(left)
      leftHT.tail.right = node
      node.left = leftHT.tail
      node.right = rightHT.head
      rightHT.head.left = node
      return HeadTail(leftHT.head, rightHT.tail)
    }

    return recurse(root).head
  }
}

data class HeadTail(val head: Node, val tail: Node)
 class Node(var `val`: Int) {
   var left: Node? = null
   var right: Node? = null
 }