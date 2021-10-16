package problems.problem0148

import datastructures.ListNode

class Solution {
  fun sortList(head: ListNode?): ListNode? {
    if (head == null) {
      return null
    }
    val list = mutableListOf<ListNode>()
    var iter = head
    while (iter != null) {
      list.add(iter)
      iter = iter.next
    }
    list.sortBy { it.`val` }
    for (i in list.indices) {
      if (i == list.size - 1) {
        list[i].next = null
      }
      else {
        list[i].next = list[i + 1]
      }
    }
    return list[0]
  }
}