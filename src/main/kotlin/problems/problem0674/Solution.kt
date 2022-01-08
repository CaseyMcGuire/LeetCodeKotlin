package problems.problem0674

class Solution {
  fun findLengthOfLCIS(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }

    var longestSoFar = 1
    var curLength = 1
    for (i in 1 until nums.size) {
      if (nums[i - 1] < nums[i]) {
        curLength++
      }
      else {
        curLength = 1
      }
      if (curLength > longestSoFar) {
        longestSoFar = curLength
      }
    }
    return longestSoFar
  }
}