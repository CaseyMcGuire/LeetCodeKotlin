package problems.problem0456

import java.util.TreeMap

class Solution {
  fun find132pattern(nums: IntArray): Boolean {
    val leftNumsToFrequency = TreeMap<Int, Int>()
    val rightNumsToFrequency = TreeMap<Int, Int>()

    for (num in nums) {
      leftNumsToFrequency.increment(num)
    }

    for (i in nums.indices.reversed()) {
      val num = nums[i]
      leftNumsToFrequency.decrement(num)
      val rightNum = rightNumsToFrequency.lowerKey(num)
      if (rightNum != null) {
        val leftNum = leftNumsToFrequency.lowerKey(rightNum)
        if (leftNum != null) {
          return true
        }
      }
      rightNumsToFrequency.increment(num)
    }
    return false
  }

  private fun TreeMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(num: Int) {
    val frequency = this[num]
      ?: return

    if (frequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = frequency - 1
    }
  }
}