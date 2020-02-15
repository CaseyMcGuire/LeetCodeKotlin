package problems.problem0001

import java.lang.IllegalArgumentException

class Solution {
  fun twoSum(nums: IntArray, target: Int): IntArray {
    val numToIndex = mutableMapOf<Int, Int>()
    for ((index, value) in nums.withIndex()) {
      val remainder = target - value
      val otherIndex = numToIndex[remainder]
      if (otherIndex != null) {
        return listOf(index, otherIndex).toIntArray()
      }
      numToIndex[value] = index
    }
    throw IllegalArgumentException()
  }
}