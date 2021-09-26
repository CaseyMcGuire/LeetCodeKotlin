package problems.problem0061

import datastructures.ListNode

class Solution {
  fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if (head == null) {
      return null
    }
    val length = head.length()
    var rotations = k % length
    if (rotations == 0) {
      return head
    }

    val tailLength = length - rotations - 1
    var current = head
    var i = 0
    while (i < tailLength) {
      current = current!!.next
      i++
    }

    val newHead = current!!.next
    current.next = null

    var tail = newHead
    while (tail!!.next != null) {
      tail = tail.next
    }
    tail!!.next = head

    return newHead
  }

  fun ListNode.length(): Int {
    var current: ListNode? = this
    var num = 0
    while (current != null) {
      num++
      current = current?.next
    }
    return num
  }
}