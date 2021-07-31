package problems.problem0053

class Solution {
  fun maxSubArray(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    var maxAtLastIndex = nums[0]
    var maxSoFar = nums[0]
    for (i in 1 until nums.size) {
      val sumWithLastIndex = nums[i] + maxAtLastIndex
      val currentSum = if (sumWithLastIndex > nums[i]) {
        maxAtLastIndex + nums[i]
      }
      else {
        nums[i]
      }
      if (currentSum > maxSoFar) {
        maxSoFar = currentSum
      }
      maxAtLastIndex = currentSum
    }
    return maxSoFar
  }
}

fun main(args: Array<String>) {
  println(Solution().maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
}