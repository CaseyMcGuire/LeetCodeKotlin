package problems.problem0683

import java.util.*

class Solution {
  fun kEmptySlots(bulbs: IntArray, k: Int): Int {
    val turnedIndices = TreeSet<Int>()

    for (i in bulbs.indices) {
      val indexToTurn = bulbs[i]
      val lowerIndex = turnedIndices.lower(indexToTurn)
      if (lowerIndex != null) {
        val distance = indexToTurn - lowerIndex - 1
        if (distance == k) {
          return i + 1
        }
      }

      val higherIndex = turnedIndices.higher(indexToTurn)
      if (higherIndex != null) {
        val distance = higherIndex - indexToTurn - 1
        if (distance == k) {
          return i + 1
        }
      }
      turnedIndices.add(indexToTurn)
    }
    return -1
  }
}