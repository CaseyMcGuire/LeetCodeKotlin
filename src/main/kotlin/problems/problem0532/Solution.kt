package problems.problem0532

class Solution {
  fun findPairs(nums: IntArray, k: Int): Int {
    val frequencyMap = createFrequencyMap(nums)
    val uniquePairs = mutableSetOf<Pair<Int, Int>>()
    for (num in nums) {
      frequencyMap.decrement(num)
      val containsPositive = frequencyMap[num - k] != null
      val containsNegative = frequencyMap[num + k] != null
      if (containsPositive) {
        uniquePairs.add(getPair(num - k, num))
      }
      if (containsNegative) {
        uniquePairs.add(getPair(num + k, num))
      }
    }
    return uniquePairs.size
  }

  private fun getPair(first: Int, second: Int): Pair<Int, Int> {
    if (first < second) {
      return Pair(first, second)
    }
    else {
      return Pair(second, first)
    }
  }

  private fun createFrequencyMap(nums: IntArray): MutableMap<Int, Int> {
    val frequencyMap = mutableMapOf<Int, Int>()
    for (num in nums) {
      frequencyMap.merge(num, 1) { cur, acc -> cur + acc }
    }

    return frequencyMap
  }

  private fun MutableMap<Int, Int>.decrement(num: Int) {
    val currentFrequency = this[num] ?: return
    if (currentFrequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = currentFrequency - 1
    }
  }
}