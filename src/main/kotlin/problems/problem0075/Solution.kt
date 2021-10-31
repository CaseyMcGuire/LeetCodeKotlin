package problems.problem0075

class Solution {
  fun sortColors(nums: IntArray): Unit {
    var first = 0
    var second = 0
    var third = 0
    for (num in nums) {
      if (num == 0) {
        first++
      }
      else if (num == 1) {
        second++
      }
      else {
        third++
      }
    }

    for (i in nums.indices) {
      if (first > 0) {
        nums[i] = 0
        first--
      }
      else if (second > 0) {
        nums[i] = 1
        second--
      }
      else {
        nums[i] = 2
        third--
      }
    }
  }
}