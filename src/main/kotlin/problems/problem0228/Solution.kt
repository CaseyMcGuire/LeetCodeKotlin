package problems.problem0228

class Solution {
  fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) {
      return emptyList()
    }
    if (nums.size == 1) {
      return listOf(nums[0].toString())
    }
    var currentPair = Pair(nums[0], nums[0])

    val pairs = mutableListOf<Pair>()
    for (i in nums.indices) {
      if (i == 0) {
        continue
      }
      val curNum = nums[i]
      if (curNum - 1 == currentPair.second) {
        currentPair.second = curNum
      }
      else {
        pairs.add(currentPair)
        currentPair = Pair(curNum, curNum)
      }
    }
    pairs.add(currentPair)

    val ranges = mutableListOf<String>()
    for (pair in pairs) {
      if (pair.first == pair.second) {
        ranges.add(pair.first.toString())
      }
      else {
        ranges.add("${pair.first}->${pair.second}")
      }
    }
    return ranges
  }
}

data class Pair(val first: Int, var second: Int)