package problems.problem0360

class Solution {
  fun sortTransformedArray(nums: IntArray, a: Int, b: Int, c: Int): IntArray {
    return nums.map { a * Math.pow(it.toDouble(), 2.0).toInt() + b * it + c }.sorted().toIntArray()
  }
}