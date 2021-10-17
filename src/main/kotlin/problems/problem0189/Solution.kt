package problems.problem0189

class Solution {
  fun rotate(nums: IntArray, k: Int): Unit {
    if (k == 0) {
      return
    }
    val extra = IntArray(nums.size)
    val modK = k % nums.size
    if (modK == 0) {
      return
    }
    var cur = 0
    var iter = nums.size - modK
    while (cur < nums.size) {
      if (iter == nums.size) {
        iter = 0
      }
      extra[cur] = nums[iter]
      cur++
      iter++
    }

    for (i in nums.indices) {
      nums[i] = extra[i]
    }
  }
}