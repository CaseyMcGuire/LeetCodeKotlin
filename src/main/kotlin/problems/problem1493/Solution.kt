package problems.problem1493

class Solution {
  fun longestSubarray(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    var i = 0
    var j = 1
    var longestSubarraySoFar = 0
    var numZeroes = if (nums[0] == 0) 1 else 0
    while (true) {
      if (numZeroes > 1) {
        if (nums[i] == 0) {
          numZeroes--
        }
        i++
        continue
      }

      // minus 1 because we must delete one
      var length = j - i - 1

      if (length > longestSubarraySoFar) {
        longestSubarraySoFar = length
      }

      if (j >= nums.size) {
        break
      }

      if (nums[j] == 0) {
        numZeroes++
      }
      j++
    }

    return longestSubarraySoFar
  }
}