package problems.problem0217

class Solution {
  fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    for (num in nums) {
      if (!set.add(num)) {
        return true
      }
    }
    return false
  }
}