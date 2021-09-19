package problems.problem0268

class Solution {
  fun missingNumber(nums: IntArray): Int {
    val numsSorted = nums.sorted()
    for (i in nums.indices) {
      if (numsSorted[i] != i) {
        return i
      }
    }
    return nums.size
  }
}