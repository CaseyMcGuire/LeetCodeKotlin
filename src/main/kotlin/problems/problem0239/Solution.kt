package problems.problem0239

import java.util.*

class Solution {
  fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val frequencyMap = TreeMap<Int, Int>()
    val maximums = mutableListOf<Int>()
    for (i in 0 until k) {
      frequencyMap.increment(nums[i])
    }
    val largest = frequencyMap.lastKey()
    maximums.add(largest)
    for (i in 1..nums.size - k) {
      frequencyMap.decrement(nums[i - 1])
      frequencyMap.increment(nums[i + k - 1])
      maximums.add(frequencyMap.lastKey())
    }
    return maximums.toIntArray()
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
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