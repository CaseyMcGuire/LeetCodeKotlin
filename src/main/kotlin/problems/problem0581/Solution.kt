package problems.problem0581

class Solution {
  fun findUnsortedSubarray(nums: IntArray): Int {
    val numsSorted = nums.sorted()
    var index = 0
    var start = -1
    while (index < nums.size) {
      if (nums[index] != numsSorted[index]) {
        start = index
        break
      }
      index++
    }
    if (start == -1) {
      return 0
    }
    var end = start
    while (index < nums.size) {
      if (nums[index] != numsSorted[index]) {
        end = index
      }
      index++
    }

    return end - start + 1
  }
}