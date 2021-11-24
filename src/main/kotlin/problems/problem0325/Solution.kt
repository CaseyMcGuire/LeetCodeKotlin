package problems.problem0325

class Solution {
  fun maxSubArrayLen(nums: IntArray, k: Int): Int {
    val runningSum = IntArray(nums.size)
    for (i in runningSum.indices) {
      if (i == 0) {
        runningSum[i] = nums[i]
      }
      else {
        runningSum[i] = nums[i] + runningSum[i - 1]
      }
    }
    for (windowSize in nums.size downTo 1) {
      var sum = runningSum[windowSize - 1]

      if (sum == k) {
        return windowSize
      }
      for (i in windowSize until nums.size) {
        sum -= nums[i - windowSize]
        sum += nums[i]
        if (sum == k) {
          return windowSize
        }
      }
    }
    return 0
  }
}