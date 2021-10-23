package problems.problem0234

import datastructures.ListNode

class Solution {
  fun isPalindrome(head: ListNode?): Boolean {
    if (head == null) {
      return false
    }
    val list = mutableListOf<Int>()
    var cur = head
    while (cur != null) {
      list.add(cur.`val`)
      cur = cur.next
    }
    var start = 0
    var end = list.size - 1
    while (start < end) {
      if (list[start] != list[end]) {
        return false
      }
      start++
      end--
    }
    return true
  }
}