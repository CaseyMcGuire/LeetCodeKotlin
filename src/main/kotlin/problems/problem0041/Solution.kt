package problems.problem0041

class Solution {
  fun firstMissingPositive(nums: IntArray): Int {
    var i = 0
    while (i < nums.size) {
      val cur = nums[i]

      if (cur <= 0 || cur > nums.size) {
        i++
        continue
      }
      val correctIndex = cur - 1
      if (i == correctIndex || nums[correctIndex] == cur) {
        i++
        continue
      }
      val temp = nums[correctIndex]
      nums[correctIndex] = nums[i]
      nums[i] = temp
    }

    for (i in nums.indices) {
      if (nums[i] != i + 1) {
        return i + 1
      }
    }
    return nums.size + 1
  }
}