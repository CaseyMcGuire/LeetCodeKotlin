package problems.problem0055

class Solution {
  fun canJump(nums: IntArray): Boolean {
    val canReachEnd: MutableList<Boolean?> = nums.map { null }.toMutableList()
    for (i in nums.size - 1 downTo 0) {
      if (i == nums.size - 1) {
        canReachEnd[i] = true
        continue
      }
      val jumpLength = nums[i]
      var canJump = false
      for (j in i + 1..i + jumpLength) {
        if (j >= nums.size) {
          break
        }
        if (canReachEnd[j] == true) {
          canJump = true
          break
        }
      }
      canReachEnd[i] = canJump
    }
    return canReachEnd[0] == true
  }
}