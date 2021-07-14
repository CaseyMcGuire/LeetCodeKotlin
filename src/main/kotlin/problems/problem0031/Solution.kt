package problems.problem0031

import java.util.*

class Solution {
  fun nextPermutation(nums: IntArray): Unit {
    val firstIndex = findFirstNonMonotonicallyIncreasingIndex(nums)
    if (firstIndex == null) {
      nums.sort()
      return
    }
    var smallestGreaterNumberIndex: Int? = null
    val startRange = firstIndex + 1
    for (i in startRange until nums.size) {
      if (smallestGreaterNumberIndex == null) {
        smallestGreaterNumberIndex = i
      }
      else if (nums[i] < nums[smallestGreaterNumberIndex] && nums[i] > nums[firstIndex]) {
        smallestGreaterNumberIndex = i
      }
    }
    val temp = nums[firstIndex]
    nums[firstIndex] = nums[smallestGreaterNumberIndex!!]
    nums[smallestGreaterNumberIndex!!] = temp
    Arrays.sort(nums, firstIndex + 1, nums.size)
  }

  private fun findFirstNonMonotonicallyIncreasingIndex(nums: IntArray): Int? {
    for (i in nums.size - 2 downTo 0) {
      if (nums[i] < nums[i + 1]) {
        return i
      }
    }
    return null
  }
}

fun main(args: Array<String>) {
  Solution().nextPermutation(intArrayOf(1,5,7,4))
  Solution().nextPermutation(intArrayOf(1,2,3,4,5))
  Solution().nextPermutation(intArrayOf(2,3,1))
}