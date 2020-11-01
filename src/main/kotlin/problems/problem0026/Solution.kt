package problems.problem0026

class Solution {
  fun removeDuplicates(nums: IntArray): Int {
    if (nums.size <= 1) {
      return nums.size
    }
    var currentIndex = 1
    var currentLength = 1
    var previousNum = nums[0]
    while (currentIndex < nums.size) {
      if (nums[currentIndex] != previousNum) {
        nums[currentLength] = nums[currentIndex]
        previousNum = nums[currentIndex]
        currentLength++
      }
      currentIndex++
    }
    return currentLength
  }
}