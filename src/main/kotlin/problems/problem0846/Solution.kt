package problems.problem0846

import java.util.*

class Solution {
  fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
    if (hand.size % groupSize != 0) {
      return false
    }
    val frequencyMap = TreeMap<Int, Int>()
    for (card in hand) {
      frequencyMap.increment(card)
    }

    val numGroups = hand.size / groupSize
    for (i in 0 until numGroups) {
      var lowest = frequencyMap.firstKey()
      for (j in lowest until lowest + groupSize) {
        if (!frequencyMap.decrement(j)) {
          return false
        }
      }
    }
    return true
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int): Boolean {
    val currentFrequency = this[num] ?: return false
    if (currentFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = currentFrequency - 1
    }
    return true
  }
}