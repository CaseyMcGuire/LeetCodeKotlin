package problems.problem0384

class Solution(private val nums: IntArray) {

  fun reset(): IntArray {
    return nums
  }

  fun shuffle(): IntArray {
    return this.nums.toMutableList().shuffled().toIntArray()
  }

}