package problems.problem0945

class Solution {
  fun minIncrementForUnique(nums: IntArray): Int {
    if (nums.isEmpty()) {
      return 0
    }
    nums.sort()
    var numMoves = 0
    var curMin = nums[0]
    for (i in 1 until nums.size) {
      if (nums[i] <= curMin) {
        numMoves += curMin - nums[i] + 1
        curMin++
      }
      else {
        curMin = nums[i]
      }
    }
    return numMoves
  }
}