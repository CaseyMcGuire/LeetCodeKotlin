package problems.problem0560

class Solution {
  fun subarraySum(nums: IntArray, k: Int): Int {
    val sumMap = mutableMapOf<Int, Int>()
    var sum = 0
    for (num in nums) {
      sum += num
      sumMap.increment(sum)
    }
    var numSubarrays = 0
    var runningSum = 0
    for (i in nums.indices) {
      val elem = nums[i]
      val toSearch = runningSum + k
      val indexTotal = sumMap[toSearch] ?: 0
      numSubarrays += indexTotal
      runningSum += nums[i]
      sumMap.decrement(runningSum)
    }
    return numSubarrays
  }

  private fun MutableMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Int, Int>.decrement(num: Int) {
    val current = this[num] ?: return
    if (current == 1) {
      this.remove(num)
    }
    else {
      this[num] = current - 1
    }
  }
}