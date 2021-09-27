package problems.problem1929

class Solution {
  fun getConcatenation(nums: IntArray): IntArray {
    val list = nums.toList()
    return (list + list).toIntArray()
  }
}