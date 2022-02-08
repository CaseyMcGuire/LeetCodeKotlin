package problems.problem1090

import java.util.*

class Solution {
  fun largestValsFromLabels(values: IntArray, labels: IntArray, numWanted: Int, useLimit: Int): Int {
    val pq = PriorityQueue<Item>(compareByDescending({ it.value }))
    for (i in values.indices) {
      pq.add(Item(values[i], labels[i]))
    }

    val labelToUsages = mutableMapOf<Int, Int>()
    var totalItems = 0
    var score = 0
    while (pq.isNotEmpty() && totalItems < numWanted) {
      val currentItem = pq.poll()
      val usages = labelToUsages[currentItem.label] ?: 0
      if (usages == useLimit) {
        continue
      }

      score += currentItem.value
      labelToUsages.merge(currentItem.label, 1) { cur, acc -> cur + acc }
      totalItems++
    }

    return score
  }
}

data class Item(val value: Int, val label: Int)