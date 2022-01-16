package problems.problem1019

import datastructures.ListNode
import java.util.*

class Solution {
  fun nextLargerNodes(head: ListNode?): IntArray {
    val valueToIndices = TreeMap<Int, MutableSet<Int>>()

    val list = mutableListOf<Int>()
    var cur = head
    var i = 0
    while (cur != null) {

      val smallerElements = valueToIndices.headMap(cur.`val`, false)
      for (elem in smallerElements.entries) {
        for (index in elem.value) {
          list[index] = cur.`val`
        }
      }
      smallerElements.keys.toSet().forEach { valueToIndices.remove(it) }
      list.add(0)
      val indexSet = valueToIndices[cur.`val`] ?: mutableSetOf()
      indexSet.add(i)
      valueToIndices[cur.`val`] = indexSet
      i++
      cur = cur.next
    }
    return list.toIntArray()
  }
}