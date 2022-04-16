package problems.problem0713

class Solution {
  fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    if (nums.isEmpty()) {
      return 0
    }
    var count = 0
    var windowProduct = nums[0]
    var i = 0
    var j = 1
    while (true) {
      if (windowProduct < k) {
        count += (j - i)
      }

      if (j == i || windowProduct < k) {
        if (j == nums.size) {
          break
        }
        windowProduct *= nums[j]
        j++
      }
      else {
        windowProduct /= nums[i]
        i++
      }
    }
    return count
  }
}