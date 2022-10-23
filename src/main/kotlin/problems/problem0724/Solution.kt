package problems.problem0724

class Solution {
  fun pivotIndex(nums: IntArray): Int {
    if (nums.size == 1) {
      return 0
    }
    var rightSum = nums.sum()
    var leftSum = 0
    for (i in nums.indices) {
      rightSum -= nums[i]
      if (rightSum == leftSum) {
        return i
      }
      leftSum += nums[i]
    }
    return -1
  }
}