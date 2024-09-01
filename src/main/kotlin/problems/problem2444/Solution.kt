package problems.problem2444

class Solution {
  fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
    var sum = 0L
    var lastMinIndex = -1
    var lastMaxIndex = -1
    var leftBound = -1
    for (i in nums.indices) {
      val num = nums[i]
      if (num !in minK..maxK) {
        lastMinIndex = -1
        lastMaxIndex = -1
        leftBound = i
      }

      if (num == minK) {
        lastMinIndex = i
      }

      if (num == maxK) {
        lastMaxIndex = i
      }

      if (lastMaxIndex != -1 && lastMinIndex != -1) {
        sum += Math.min(lastMaxIndex, lastMinIndex) - leftBound
      }
    }
    return sum
  }
}