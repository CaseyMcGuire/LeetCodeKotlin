package problems.problem0209

class Solution {
  fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var minSoFar: Int? = null
    var i = 0
    var j = 0
    var curSum = 0
    while (true) {
      if (curSum < target || i == j) {
        if (j == nums.size) {
          break
        }
        curSum += nums[j]
        j++
      }
      else {
        curSum -= nums[i]
        i++
      }

      if (curSum >= target) {
        val currentLength = j - i
        if (minSoFar == null || minSoFar > currentLength) {
          minSoFar = currentLength
        }
      }
    }
    return minSoFar ?: 0
  }
}