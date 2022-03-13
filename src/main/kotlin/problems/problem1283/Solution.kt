package problems.problem1283

class Solution {
  fun smallestDivisor(nums: IntArray, threshold: Int): Int {
    val max = nums.max()!!
    var low = 1
    var high = max
    var minSoFar = Integer.MAX_VALUE
    while (low <= high) {
      val mid = ((low.toBigInteger() + high.toBigInteger()) / 2.toBigInteger()).toInt()
      if (canDivideBelowThreshold(nums, mid, threshold)) {
        minSoFar = mid
        high = mid - 1
      }
      else {
        low = mid + 1
      }

    }
    return minSoFar
  }

  private fun canDivideBelowThreshold(nums: IntArray, divisor: Int, threshold: Int): Boolean {
    var sum = 0
    for (num in nums) {
      sum += num / divisor
      if (num % divisor != 0) {
        sum++
      }
    }
    return sum <= threshold
  }
}