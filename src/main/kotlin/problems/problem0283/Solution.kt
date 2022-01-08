package problems.problem0283

class Solution {
  fun moveZeroes(nums: IntArray): Unit {
    var pointer = 0
    for (i in nums.indices) {
      if (nums[i] != 0) {
        nums[pointer] = nums[i]
        if (pointer != i) {
          println("hello")
          nums[i] = 0
        }
        pointer++
      }
    }
  }
}