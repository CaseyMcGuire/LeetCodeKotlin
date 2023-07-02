package problems.problem0643

class Solution {
  fun findMaxAverage(nums: IntArray, k: Int): Double {
    var sum = 0
    for (i in 0 until k) {
      sum += nums[i]
    }
    var maxAverageSoFar = sum.toDouble() / k.toDouble()
    for (i in k until nums.size) {
      sum -= nums[i - k]
      sum += nums[i]
      val curAverage = sum.toDouble() / k.toDouble()
      maxAverageSoFar = Math.max(curAverage, maxAverageSoFar)
    }
    return maxAverageSoFar
  }
}