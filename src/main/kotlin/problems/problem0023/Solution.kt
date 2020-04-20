package problems.problem0023

import datastructures.ListNode
import java.util.*

class Solution {
  fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) {
      return null
    }

    val pq = PriorityQueue<ListNode>(kotlin.Comparator { o1, o2 ->  if(o1.`val`  > o2.`val`)  1 else -1});
    pq.addAll(lists.filterNotNull())
    var head: ListNode? = null
    var currentNode : ListNode? = null

    while (!pq.isEmpty()) {
      val top = pq.poll()
      val next = top.next
      if (head == null) {
        head = top
        currentNode = top
      }
      else {
        currentNode!!.next = top
        currentNode = currentNode.next
      }
      if (next != null) {
        pq.add(next)
      }
    }
    return head
  }
}