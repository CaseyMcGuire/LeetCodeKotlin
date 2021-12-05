package problems.problem1438

import java.util.*

class Solution {
  fun longestSubarray(nums: IntArray, limit: Int): Int {
    if (nums.isEmpty()) {
      return 0
    }
    val frequencyMap = TreeMap<Int, Int>()
    var start = 0
    var end = 1
    var maxSoFar = 1
    var currentLength = 1
    frequencyMap.increment(nums[0])
    while (end <= nums.size) {
      val smallest = frequencyMap.firstKey()
      val largest = frequencyMap.lastKey()
      val difference = Math.abs(smallest - largest)
      if (difference > limit) {
        val startElement = nums[start]
        frequencyMap.decrement(startElement)
        currentLength--
        start++
      }
      else {
        if (currentLength > maxSoFar) {
          maxSoFar = currentLength
        }
        if (end == nums.size) {
          break
        }
        currentLength++
        val curElement = nums[end]
        frequencyMap.increment(curElement)
        end++
      }
    }
    return maxSoFar
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val currentFrequency = this[num]
    if (currentFrequency == null) {
      return
    }

    if (currentFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = currentFrequency - 1
    }
  }
}