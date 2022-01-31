package problems.problem0453

class Solution {
  fun minMoves(nums: IntArray): Int {
    // this is equivalent decrementing each number by 1 until it reaches the min
    var min = nums.min()!!
    var moves = 0
    for (num in nums) {
      moves += num - min
    }
    return moves
  }
}