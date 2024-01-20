package problems.problem1004

class Solution {
  fun longestOnes(nums: IntArray, k: Int): Int {
    if (nums.isEmpty()) {
      return 0
    }
    var i = 0
    var j = 0
    var numZeroes = 0
    var longestRun = 0
    while (true) {
      if (numZeroes > k) {
        val value = nums[i]
        if (value == 0) {
          numZeroes--
        }
        i++
      }

      val length = j - i
      if (length > longestRun) {
        longestRun = length
      }
      if (j == nums.size) {
        break
      }
      val end = nums[j]
      if (end == 0) {
        numZeroes++
      }
      j++
    }
    return longestRun
  }
}