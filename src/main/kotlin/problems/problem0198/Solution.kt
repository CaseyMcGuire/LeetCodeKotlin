package problems.problem0198

class Solution {
  fun rob(nums: IntArray): Int {
    val maxAtPlace = IntArray(nums.size)
    for (i in 0 until nums.size) {
      if (i == 0 || i == 1) {
        maxAtPlace[i] = nums[i]
        continue
      }
      if (i == 2) {
        maxAtPlace[i] = nums[i] + maxAtPlace[0]
        continue
      }
      maxAtPlace[i] = nums[i] + greater(maxAtPlace[i - 2], maxAtPlace[i - 3])
    }
    return maxAtPlace.max() ?: 0
  }

  fun greater(num1: Int, num2: Int): Int {
    if (num1 > num2) {
      return num1
    }
    return num2
  }
}