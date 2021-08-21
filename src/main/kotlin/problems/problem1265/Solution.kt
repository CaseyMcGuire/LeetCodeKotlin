package problems.problem1265

import java.util.*

class Solution {
  fun printLinkedListInReverse(head: ImmutableListNode?) {
    val deque = ArrayDeque<ImmutableListNode>()
    var currentNode: ImmutableListNode? = head
    while (currentNode != null) {
      deque.push(currentNode)
      currentNode = currentNode.getNext()
    }
    while (deque.isNotEmpty()) {
      deque.pop().printValue()
    }
  }
}

 interface ImmutableListNode {
   fun getNext(): ImmutableListNode?
   fun printValue()
 }