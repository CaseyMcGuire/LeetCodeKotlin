package problems.problem0442

class Solution {
  // needed hint
  fun findDuplicates(nums: IntArray): List<Int> {
    for (i in nums.indices) {
      val num = Math.abs(nums[i])
      val index = num - 1
      nums[index] = -nums[index]
    }

    val duplicates = mutableSetOf<Int>()
    for (num in nums) {
      val adjustedIndex = Math.abs(num) - 1
      if (nums[adjustedIndex] > 0) {
        duplicates.add(Math.abs(num))
      }
    }
    return duplicates.toList()
  }
}