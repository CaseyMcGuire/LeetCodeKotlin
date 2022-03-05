package problems.problem0494

class Solution {
  fun findTargetSumWays(nums: IntArray, target: Int): Int {
    var numWays = 0

    fun dfs(index: Int, curSum: Int) {
      if (index == nums.size) {
        if (curSum == target) {
          numWays++
        }
        return
      }
      dfs(index + 1, curSum + nums[index])
      dfs(index + 1, curSum - nums[index])
    }
    dfs(0, 0)
    return numWays
  }
}