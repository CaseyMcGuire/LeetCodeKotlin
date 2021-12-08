package problems.problem0328

import datastructures.ListNode

class Solution {
  fun oddEvenList(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    if (head.next == null) {
      return head
    }

    val oddHead = head
    val evenHead = head.next

    var curOdd = oddHead
    var curEven = evenHead
    while (true) {
      curOdd?.next = curOdd?.next?.next
      curEven?.next = curEven?.next?.next
      if (curEven.next == null) {
        if (curOdd?.next != null) {
          curOdd = curOdd?.next
        }
        break
      }
      curOdd = curOdd?.next
      curEven = curEven?.next
    }
    curOdd?.next = evenHead
    return oddHead
  }
}