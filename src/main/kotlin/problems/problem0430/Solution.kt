package problems.problem0430

class Solution {
  fun flatten(root: Node?): Node? {
    if (root == null) {
      return null
    }
    return recurse(root).head
  }

  fun recurse(node: Node): LinkedListNode {
    var iter = node
    val head = node
    while (true) {
      val child = iter.child
      val next = iter.next
      if (child != null) {
        val list = recurse(child)
        list.head.prev = iter
        iter.next = list.head
        val tail = list.tail
        tail.next = next
        next?.prev = tail
        iter.child = null
        if (next == null) {
          return LinkedListNode(head, tail)
        }
        iter = next
      }
      else {
        if (next == null) {
          return LinkedListNode(head, iter)
        }
        iter = next
      }
    }
  }
}

data class LinkedListNode(val head: Node, val tail: Node)
 class Node(var `val`: Int) {
     var prev: Node? = null
     var next: Node? = null
     var child: Node? = null
}