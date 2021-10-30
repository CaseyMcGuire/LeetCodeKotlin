package problems.problem0162

class Solution {
  fun findPeakElement(nums: IntArray): Int {
    var start = 0
    var end = nums.size - 1
    while (start <= end) {
      val middle = (start + end) / 2
      val elem = nums[middle]
      val greaterThanLeft = if (middle - 1 < 0) true else elem > nums[middle - 1]
      val greaterThanRight = if (middle + 1 >= nums.size) true else elem > nums[middle + 1]
      if (greaterThanLeft && greaterThanRight) {
        return middle
      }
      else if (greaterThanLeft) {
        start = middle + 1
      }
      else {
        end = middle - 1
      }
    }
    return -1
  }
}