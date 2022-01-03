package problems.problem0480

import java.util.*

class Solution {
  fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
    if (k == 1) {
      return nums.map { it.toDouble() }.toDoubleArray()
    }
    val leftWindow = FrequencyMap()
    val rightWindow = FrequencyMap()
    for (i in 0 until k) {
      rightWindow.increment(nums[i])
    }

    fun rebalance() {
      while (rightWindow.size - leftWindow.size > 1) {
        val first = rightWindow.first()
        rightWindow.decrement(first)
        leftWindow.increment(first)
      }
      while (rightWindow.first() < leftWindow.last()) {
        val first = rightWindow.first()
        val last = leftWindow.last()
        rightWindow.decrement(first)
        rightWindow.increment(last)
        leftWindow.decrement(last)
        leftWindow.increment(first)
      }
    }
    rebalance()
    val medians = DoubleArray(nums.size - k + 1)
    medians[0] = calculateMedian(leftWindow, rightWindow)
    var resultIndex = 1
    for (i in k until nums.size) {
      if (!leftWindow.decrement(nums[i - k])) {
        rightWindow.decrement(nums[i - k])
      }

      rightWindow.increment(nums[i])
      rebalance()
      medians[resultIndex] = calculateMedian(leftWindow, rightWindow)
      resultIndex++
    }

    return medians
  }

  private fun calculateMedian(left: FrequencyMap, right: FrequencyMap): Double {
    val totalSize = left.size + right.size
    if (totalSize % 2 == 1) {
      return right.first().toDouble()
    }
    else {
      return (right.first().toDouble() + left.last().toDouble()) / 2.0
    }
  }
}

class FrequencyMap() {
  val map = TreeMap<Int, Int>()
  var size = 0

  fun increment(num: Int) {
    this.map.merge(num, 1) { cur, acc -> cur + acc }
    size++
  }

  fun decrement(num: Int): Boolean {
    val currentFrequency = this.map[num] ?: return false
    if (currentFrequency == 1) {
      this.map.remove(num)
    }
    else {
      map[num] = currentFrequency - 1
    }
    this.size--
    return true
  }

  fun first(): Int {
    return map.firstKey()
  }

  fun last(): Int {
    return map.lastKey()
  }

  override fun toString(): String {
    return map.toString()
  }
}