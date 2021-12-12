package problems.problem0949

import java.util.*

class Solution {
  fun largestTimeFromDigits(arr: IntArray): String {
    val frequencyMap = TreeMap<Int, Int>()
    for (num in arr) {
      frequencyMap.increment(num)
    }
    fun recurse(index: Int, curList: MutableList<Int>): MutableList<Int>? {
      if (index == 4) {
        return curList.toMutableList()
      }
      val max =
        if (index == 0) {
          2
        }
        else if (index == 1) {
          if (curList[index - 1] == 2) 3 else 9
        }
        else if (index == 2) {
          5
        }
        else {
          9
        }
      val possibleNums = frequencyMap.headMap(max, true).keys.toList().sorted().reversed()
      if (possibleNums.isEmpty()) {
        return null
      }
      for (num in possibleNums) {
        curList.add(num)
        frequencyMap.decrement(num)
        val found = recurse(index + 1, curList)
        if (found != null) {
          return found
        }
        curList.removeAt(curList.size - 1)
        frequencyMap.increment(num)
      }
      return null
    }
    val largest = recurse(0, mutableListOf())
    if (largest == null) {
      return ""
    }
    val largestStr = largest.map { it.toString() }
    val builder = StringBuilder()
    for (i in largestStr.indices) {
      builder.append(largestStr[i])
      if (i == 1) {
        builder.append(":")
      }
    }
    return builder.toString()
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { acc, cur -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val currentFrequency = this[num] ?: return
    if (currentFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = currentFrequency - 1
    }
  }
}