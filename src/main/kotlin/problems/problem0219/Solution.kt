package problems.problem0219

class Solution {
  fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val window = mutableSetOf<Int>()

    // build initial window
    for (i in 0 until k) {
      if (i >= nums.size) {
        return false
      }
      // we found duplicate
      if (!window.add(nums[i])) {
        return true
      }
    }

    for (i in k until nums.size) {
      if (!window.add(nums[i])) {
        return true
      }
      window.remove(nums[i - k])
    }
    return false
  }
}