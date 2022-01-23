package problems.problem1331

import java.util.*

class Solution {
  fun arrayRankTransform(arr: IntArray): IntArray {
    val elemToIndex = mutableMapOf<Int, MutableList<Int>>()
    for (i in arr.indices) {
      val list = elemToIndex[arr[i]] ?: mutableListOf()
      list.add(i)
      elemToIndex[arr[i]] = list
    }

    val pq = PriorityQueue<Element>(compareBy({it.value}))
    for (entry in elemToIndex.entries) {
      pq.add(Element(entry.key, entry.value))
    }

    var rank = 1
    val rankedArray = IntArray(arr.size)
    while (pq.isNotEmpty()) {
      val currentElement = pq.poll()
      for (index in currentElement.indices) {
        rankedArray[index] = rank
      }
      rank++
    }
    return rankedArray
  }
}

data class Element(val value: Int, val indices: List<Int>)