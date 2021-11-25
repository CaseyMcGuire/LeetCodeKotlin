package problems.problem0708

class Solution {
  fun insert(head: Node?, insertVal: Int): Node? {
    if (head == null) {
      val node = Node(insertVal)
      node.next = node
      return node
    }

    val path = mutableSetOf<Node>()
    var curNode = head!!
    val newNode = Node(insertVal)
    while (true) {
      val next = curNode.next!!
      val currentVal = curNode.`val`
      val nextVal = next.`val`
      if (currentVal <= insertVal && nextVal >= insertVal ||
        insertVal > currentVal && insertVal > nextVal && currentVal > nextVal ||
        insertVal < currentVal && insertVal < nextVal && currentVal > nextVal ||
        !path.add(curNode)
      ) {
        curNode.next = newNode
        newNode.next = next
        break
      }
      curNode = next
    }
    return head
  }
}

class Node(var `val`: Int) {
  var next: Node? = null
}