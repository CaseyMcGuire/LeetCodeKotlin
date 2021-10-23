package problems.problem0334

import java.util.*

class Solution {
  fun increasingTriplet(nums: IntArray): Boolean {
    if (nums.size <= 2) {
      return false
    }
    val hasLargerInFront = BooleanArray(nums.size)
    val numSet = TreeSet<Int>()
    for (i in nums.size - 1 downTo 0) {
      hasLargerInFront[i] = numSet.higher(nums[i]) != null
      numSet.add(nums[i])
    }

    val numsWithLargerInFront = TreeSet<Int>()
    for (i in nums.size - 1 downTo 0) {
      val hasLarger = numsWithLargerInFront.higher(nums[i]) != null
      if (hasLarger) {
        return true
      }
      if (hasLargerInFront[i]) {
        numsWithLargerInFront.add(nums[i])
      }
    }
    return false
  }
}