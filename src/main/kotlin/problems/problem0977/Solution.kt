package problems.problem0977

import kotlin.math.pow

class Solution {
  fun sortedSquares(nums: IntArray): IntArray {
    return nums.map { it.toDouble().pow(2.toDouble()).toInt() }.sorted().toIntArray()
  }
}