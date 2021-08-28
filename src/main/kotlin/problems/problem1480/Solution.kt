package problems.problem1480

class Solution {
  fun runningSum(nums: IntArray): IntArray {
    val runningSums = mutableListOf<Int>()
    var currentRunningSum = 0
    for (num in nums) {
      currentRunningSum = currentRunningSum + num
      runningSums.add(currentRunningSum)
    }
    return runningSums.toIntArray()
  }
}