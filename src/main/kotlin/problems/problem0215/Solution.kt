package problems.problem0215

class Solution {
  fun findKthLargest(nums: IntArray, k: Int): Int {
    val sortedNums = nums.sorted()
    return sortedNums[sortedNums.size - k + 1]
  }
}